package ge.zgharbi.todocat
package managers.tasks

import data.DomainError

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
