package ge.zgharbi.todocat
package algebra.task

import algebra.DomainError

import com.sun.tools.javac.code.TypeTag

import scala.compiletime.constValue

sealed trait TasksError[T] extends DomainError[T]

case class TitleValidationError(
  override val message: String,
  override val issues: List[String],
) extends TasksError[TitleValidationError]

case class BodyValidationError(
  override val message: String,
  override val issues: List[String],
) extends TasksError[BodyValidationError]
