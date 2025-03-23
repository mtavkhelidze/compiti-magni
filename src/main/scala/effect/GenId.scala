package ge.zgharbi.todocat
package effect

import data.IdType

import zio.*

trait GenId:
  def make[T <: IdType]: Task[T]

object GenId {
  val onlyZeroes: IdType = IdType.read("00000000-0000-0000-0000-000000000000")
  def test: ZLayer[Any, Nothing, GenId] = ZLayer.succeed {
    new GenId {
      def make[T <: IdType]: Task[T] =
        ZIO.succeed(onlyZeroes.asInstanceOf[T])
    }
  }

  def live: ZLayer[Any, Nothing, GenId] =
    ZLayer.succeed {
      new GenId {
        def make[T <: IdType]: Task[T] =
          ZIO.succeed(IdType.make.asInstanceOf[T])
      }
    }
}
