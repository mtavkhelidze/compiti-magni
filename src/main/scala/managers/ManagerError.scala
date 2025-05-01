package ge.zgharbi.compiti
package managers

import scala.reflect.{classTag, ClassTag}
import scala.util.control.NoStackTrace

trait ManagerError[T: ClassTag] extends Exception, NoStackTrace {
  def message: String
  def issues: List[String] = Nil

  inline def key = classTag[T].runtimeClass.getSimpleName

  override def getMessage: String = message

  override def getCause: Throwable = cause.orNull

  def cause: Option[Throwable] = None
}
