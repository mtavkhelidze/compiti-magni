package ge.zgharbi.todocat
package effect

import data.IdType

import zio.*
import zio.test.*

opaque type TestIdType <: IdType = IdType

object GenIdTest extends ZIOSpecDefault {
  def spec = suite("GenIdTest") {
    test("make returns correct type") {
      for {
        id <- GenId[Task].make[TestIdType]
      } yield assertTrue(id.isInstanceOf[TestIdType])
    }
  }
}
