package ge.zgharbi.todocat
package algebra

import scala.reflect.{classTag, ClassTag}
import scala.util.control.NoStackTrace

trait DomainError[T: ClassTag] extends Exception, NoStackTrace {
  val message: String
  val issues: List[String] = Nil

  inline def key = classTag[T].runtimeClass.getSimpleName

  override def getMessage: String = message

  override def getCause: Throwable = cause.orNull

  def cause: Option[Throwable] = None
}
