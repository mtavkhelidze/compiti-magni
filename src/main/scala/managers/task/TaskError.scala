package ge.zgharbi.ms.id
package managers.task

import managers.ManagerError

import com.sun.tools.javac.code.TypeTag

import scala.compiletime.constValue

sealed trait TasksError[T] extends ManagerError[T]

case class TitleValidationError(
  override val message: String,
  override val issues: List[String],
) extends TasksError[TitleValidationError]

case class BodyValidationError(
  override val message: String,
  override val issues: List[String],
) extends TasksError[BodyValidationError]
