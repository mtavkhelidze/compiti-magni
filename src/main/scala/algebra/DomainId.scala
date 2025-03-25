package ge.zgharbi.todocat
package algebra

import zio.*

import java.util.UUID

opaque type DomainIdType = UUID

object DomainIdType {
  def random: DomainIdType = UUID.randomUUID

  def from(str: String): DomainIdType = UUID.fromString(str)
}

trait DomainId:
  def make[T <: DomainIdType]: Task[T]

object DomainId {
  val onlyZeroes: DomainIdType =
    DomainIdType.from("00000000-0000-0000-0000-000000000000")
  def test: ZLayer[Any, Nothing, DomainId] = ZLayer.succeed {
    new DomainId {
      def make[T <: DomainIdType]: Task[T] =
        ZIO.succeed(onlyZeroes.asInstanceOf[T])
    }
  }

  def live: ZLayer[Any, Nothing, DomainId] =
    ZLayer.succeed {
      new DomainId {
        def make[T <: DomainIdType]: Task[T] =
          ZIO.succeed(DomainIdType.random.asInstanceOf[T])
      }
    }
}
