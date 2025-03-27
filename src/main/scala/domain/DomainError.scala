package ge.zgharbi.todocat
package domain

import scala.quoted.*

sealed trait DomainError[A]:
  def error: String
  def module: String

extension [A: DomainError, B <: A](e: B)
  inline def error: String = DomainError.derivedError[B].error
  inline def module: String = summon[DomainError[A]].module

object DomainError {
  inline given derived[A]: DomainError[A] = ${ deriveImpl[A](true) }
  inline def derivedError[A]: DomainError[A] = ${ deriveImpl[A](false) }

  private def deriveImpl[A: Type](
    onlySealed: Boolean,
  )(using Quotes): Expr[DomainError[A]] = {
    import quotes.reflect.*
    val symbol = TypeRepr.of[A].typeSymbol
    if onlySealed && (!symbol.isClassDef || !(symbol.flags
        .is(Flags.Sealed) || symbol.flags.is(Flags.Enum)))
    then
      report.errorAndAbort(
        s"DomainError can only be derived for sealed traits or enums, but ${symbol.fullName} is not.",
      )
    val typeName = symbol.name
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
}
