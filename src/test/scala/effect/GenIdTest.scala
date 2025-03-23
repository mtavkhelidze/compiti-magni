package ge.zgharbi.todocat
package effect

import data.IdType

import zio.*
import zio.test.*
import zio.test.Assertion.*

opaque type TestIdType <: IdType = IdType

object GenIdTest extends ZIOSpecDefault {
  def spec =
    suite("GenId Suite")(
      test("maker returns correct type") {
        ZIO
          .service[GenId]
          .flatMap(_.make[TestIdType])
          .map(id => assertTrue(id.isInstanceOf[TestIdType]))
      },
      test("make returns correct string") {
        ZIO
          .service[GenId]
          .flatMap(_.make[TestIdType])
          .map(id => assert(id.toString)(equalTo(GenId.onlyZeroes.toString)))
      },
    ).provideLayer(GenId.test)
}
