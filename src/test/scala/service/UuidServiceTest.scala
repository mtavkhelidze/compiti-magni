package ge.zgharbi.todocat
package service

import zio.*
import zio.test.*
import zio.test.Assertion.*

import java.util.UUID

object UuidServiceTest extends ZIOSpecDefault {
  val spec = success + failure
  val throwable = RuntimeException("dummy")

  def failure = suite("UUID Service Failure Test")(
    test("should return correct error on fail to generate UUID") {
      val effect = ZIO.service[UuidService].flatMap(_.generate)
      effect.flip.map(e => assertTrue(e.isInstanceOf[CannotCreate]))
      effect.flip
        .map(_.cause.orNull)
        .map(c => assertTrue(c.isInstanceOf[Throwable]))
    },
  ).provideLayer(uuidFailureTest)

  def uuidFailureTest = ZLayer.succeed(new UuidService {

    override def generate: IO[CannotCreate, UUID] =
      ZIO.fail(CannotCreate("msg", Some(throwable)))

    override def parse(str: String): IO[InvalidString, UUID] =
      ZIO.succeed(UUID.fromString(str))
  })

  def success = suite("UUID Service Success Live")(
    test("should generate UUID") {
      ZIO
        .service[UuidService]
        .flatMap(_.generate)
        .map(uuid => assertTrue(uuid.isInstanceOf[UUID]))
    },
    test("should parse UUID") {
      ZIO
        .service[UuidService]
        .flatMap(_.parse("00000000-0000-0000-0000-000000000000"))
        .map(uuid =>
          assertTrue(
            uuid.toString == "00000000-0000-0000-0000-000000000000" && uuid
              .isInstanceOf[UUID],
          ),
        )
    },
    test("should fail with correct error when given inalid UUID") {
      val effect = ZIO.service[UuidService].flatMap(_.parse("invalid"))
      effect.flip.map(e => assertTrue(e.isInstanceOf[InvalidString]))
      effect.flip
        .map(_.cause.orNull)
        .map(c => assertTrue(c.isInstanceOf[Throwable]))
    },
  ).provideLayer(UuidService.live) @@ TestAspect.sequential
}
