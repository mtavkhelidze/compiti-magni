package ge.zgharbi.todocat
package effect

import data.IdType

import zio.*

trait GenId[F[_]]:
  def make[T <: IdType]: F[T]

object GenId {
  def apply[F[_]](using gen: GenId[F]): GenId[F] = gen

  given GenId[Task] with {
    def make[T <: IdType]: Task[T] = {
      ZIO.succeed(IdType.make.asInstanceOf[T])
    }
  }
}
