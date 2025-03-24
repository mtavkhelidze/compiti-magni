package ge.zgharbi.todocat
package lib

import zio.prelude.Validation

package object validation {

  def isLengthBetween(min: Int, max: Int)(
    s: String,
  ): Validation[String, String] =
    isLongerThan(min - 1)(s).flatMap(isShorterThan(max + 1))

  def isShorterThan(n: Int)(s: String): Validation[String, String] =
    Validation.fromPredicateWith(s"must be at most $n characters long")(s)(
      _.length <= n,
    )

  def isLongerThan(n: Int)(s: String): Validation[String, String] =
    Validation.fromPredicateWith(s"must be at least $n characters long")(s)(
      _.length >= n,
    )
}
