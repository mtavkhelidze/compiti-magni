package ge.zgharbi.app.compiti
package domain

import zio.test.*
import zio.test.Assertion.*

sealed trait SealedTraitError derives DomainError
case object SealedTraitErrorOne extends SealedTraitError
final case class SealedTraitErrorTwo(msg: String) extends SealedTraitError

enum EnumError derives DomainError {
  case EnumErrorOne extends EnumError
  case EnumErrorTwo(val msg: String) extends EnumError
}

object DomainErrorTest extends ZIOSpecDefault {
  import DomainError.*
  def spec = suite("Domain Error Suite")(
    test("deriving sealed trait has correct service tag") {
      val (e1, e2) = (SealedTraitErrorOne, SealedTraitErrorTwo("msg"))
      assertTrue(e1.module == "SealedTraitError")
      assertTrue(e2.module == "SealedTraitError")
      assertTrue(e1.error == "SealedTraitErrorOne")
      assertTrue(e2.error == "SealedTraitErrorTwo")
      assertTrue(e2.msg == "msg")
    },
    test("deriving enum has correct service tag") {
      import EnumError.*
      val e1 = EnumErrorOne
      val e2 = EnumErrorTwo(msg = "msg")
      assertTrue(e1.module == "SealedTraitError")
      assertTrue(e2.module == "SealedTraitError")
      assertTrue(e1.error == "EnumErrorOne")
      assertTrue(e2.error == "EnumErrorTwo")
      e2 match
        case EnumErrorTwo(msg) => assertTrue(msg == "msg")
        case _                 => assertTrue(false)
    },
  )
}
