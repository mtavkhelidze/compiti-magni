package ge.zgharbi.todocat
package data

import scala.compiletime.constValue
import scala.reflect.{classTag, ClassManifest, ClassTag}
import scala.util.control.NoStackTrace

trait ModuleError[Module: ClassTag] extends Exception, NoStackTrace {
  val message: String
  val issues: List[String] = Nil

  inline def key = classTag[Module].runtimeClass.getSimpleName

  override def getMessage: String = message

  override def getCause: Throwable = cause.orNull

  def cause: Option[Throwable] = None
}
