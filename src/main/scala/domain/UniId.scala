package ge.zgharbi.todocat
package domain
import zio.{IO, ZIO}

import scala.util.Try

object UniId {
  opaque type IdType = Serializable

  def toIdType[T <: Serializable](id: T): IdType = id
  def fromIdType[T <: IdType](id: IdType): IO[Throwable, T] =
    ZIO.fromTry(Try(id.asInstanceOf[T]))
}

type UniId = UniId.type
