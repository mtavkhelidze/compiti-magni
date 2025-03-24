package ge.zgharbi.todocat
package modules.tasks

import data.ModuleError

import scala.compiletime.constValue

sealed trait TaskModuleError[T] extends ModuleError[T]

case class TitleValidationError(
  override val message: String,
  override val issues: List[String],
) extends TaskModuleError[TitleValidationError]

case class BodyValidationError(
  override val message: String,
  override val issues: List[String],
) extends TaskModuleError[BodyValidationError]
