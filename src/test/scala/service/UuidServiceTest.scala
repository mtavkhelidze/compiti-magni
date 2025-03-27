package ge.zgharbi.todocat
package service

import zio.*
import zio.test.*
import zio.test.Assertion.*

import java.util.UUID

object UuidServiceSpec extends ZIOSpecDefault {

  def spec =
    suite("UuidService Spec")(
      test("create generates a UUID successfully") {
        ZIO
          .service[UuidService]
          .flatMap(_.create)
          .map(uuid => assert(uuid)(isSubtype[UUID](anything)))
      },
      test("create fails with CannotCreate error") {
        val layer = ZLayer.succeed(new UuidService {
          def create: IO[UuidError, UUID] =
            ZIO.fail(CannotCreate)
          def parse(str: String): IO[UuidError, UUID] =
            ZIO.fail(CannotParse(Some(new Exception("Parse error"))))
        })
        ZIO
          .service[UuidService]
          .flatMap(_.create)
          .provideLayer(layer)
          .either
          .map(assert(_)(isLeft(isSubtype[CannotCreate](anything))))
      },
      test("parse succeeds with valid UUID string") {
        val validUuidStr = UUID.randomUUID().toString
        ZIO
          .service[UuidService]
          .flatMap(_.parse(validUuidStr))
          .map(assert(_)(equalTo(UUID.fromString(validUuidStr))))
      },
      test("parse fails with CannotParse error for invalid UUID string") {
        val invalidUuidStr = "invalid-uuid"
        ZIO
          .service[UuidService]
          .flatMap(_.parse(invalidUuidStr))
          .flip
          .map(e =>
            assert(e)(isSubtype[CannotParse](anything)) &&
              assert(e.cause)(
                isSome(isSubtype[IllegalArgumentException](anything)),
              ),
          )
      },
    ).provideLayer(UuidService.live)
}
