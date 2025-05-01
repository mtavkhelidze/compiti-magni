package ge.zgharbi.app.compiti
package lib

import lib.validation.StringValidation.*

import zio.prelude.Validation
import zio.test.*
object StringValidationsTest extends ZIOSpecDefault {

  def spec = suite("String Validations Test")(
    test(
      "isShorterThan allows strings of length <= n and rejects longer ones",
    ) {
      check(Gen.int(1, 100), Gen.alphaNumericString) { (n, str) =>
        val result = isShorterThan(n)(str)
        if str.length <= n then assertTrue(result.isSuccess)
        else assertTrue(result.isFailure)
      }
    },
    test(
      "isLongerThan allows strings of length >= n and rejects shorter ones",
    ) {
      check(Gen.int(1, 100), Gen.alphaNumericString) { (n, str) =>
        val result = isLongerThan(n)(str)
        if str.length >= n then assertTrue(result.isSuccess)
        else assertTrue(result.isFailure)
      }
    },
    test("isLengthBetween allows strings within range and rejects others") {
      check(Gen.int(1, 50), Gen.int(51, 100), Gen.alphaNumericString) {
        (min, max, str) =>
          val result = isLengthBetween(min, max)(str)
          assertTrue(
            result.fold(
              _.forall(e => e.contains("at least") || e.contains("at most")),
              _ == str,
            ),
          )
      }
    },
  )
}
