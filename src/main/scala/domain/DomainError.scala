package ge.zgharbi.compiti
package domain

import scala.quoted.*

trait WithMessage {
  def message: String
}
trait WithIssues {
  def issues: List[String]
}

trait WithCause {
  def cause: Option[Throwable] = None
}

trait DomainError[A]:
  def error: String
  def module: String

object DomainError {
  inline given derived[A]: DomainError[A] = ${ deriveImpl[A](true) }

  private def deriveImpl[A: Type](
    onlySealed: Boolean,
  )(using quotes: Quotes): Expr[DomainError[A]] = {
    import quotes.reflect.*
    val symbol = TypeRepr.of[A].typeSymbol
    if onlySealed && (!symbol.isClassDef || !(symbol.flags
        .is(Flags.Sealed) || symbol.flags.is(Flags.Enum)))
    then
      report.errorAndAbort(
        s"DomainError can only be derived for sealed traits or enums, but ${symbol.fullName} is not.",
      )
    val typeName = symbol.name.replace("$", "")
    '{
      new DomainError[A] {
        def error: String = ${
          Expr(typeName)
        }
        def module: String = ${
          Expr(typeName)
        }
      }
    }
  }

  extension [A: DomainError, B <: A](_b: B)
    inline def error: String = DomainError.derivedError[B].error
    inline def module: String = summon[DomainError[A]].module

  private inline def derivedError[A]: DomainError[A] = ${ deriveImpl[A](false) }
}
