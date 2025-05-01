package ge.zgharbi.compiti
package managers

import domain.{DomainId, DomainIdType}
import zio.*
import zio.test.*
import zio.test.Assertion.*

opaque type TestIdType <: DomainIdType = DomainIdType

object DomainIdTest extends ZIOSpecDefault {
  def spec =
    suite("DomainId Suite")(
      test("maker returns correct type") {
        ZIO
          .service[DomainId]
          .flatMap(_.make[TestIdType])
          .map(id => assertTrue(id.isInstanceOf[TestIdType]))
      },
      test("make returns correct string") {
        ZIO
          .service[DomainId]
          .flatMap(_.make[TestIdType])
          .map(id => assert(id.toString)(equalTo(DomainId.onlyZeroes.toString)))
      },
    ).provideLayer(DomainId.test)
}
