package ge.zgharbi.todocat
package managers.task

import managers.AlgebraError

import com.sun.tools.javac.code.TypeTag

import scala.compiletime.constValue

sealed trait TasksError[T] extends AlgebraError[T]

case class TitleValidationError(
  override val message: String,
  override val issues: List[String],
) extends TasksError[TitleValidationError]

case class BodyValidationError(
  override val message: String,
  override val issues: List[String],
) extends TasksError[BodyValidationError]
