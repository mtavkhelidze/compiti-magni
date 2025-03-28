package ge.zgharbi.todocat
package service
import domain.{DomainError, WithCause}

import zio.*

import java.util.UUID
import scala.util.Try

sealed trait UuidError extends WithCause derives DomainError

case object CannotCreate extends UuidError {
  override def cause: Option[Throwable] = None
}
type CannotCreate = CannotCreate.type
case class CannotParse(override val cause: Option[Throwable]) extends UuidError

trait UuidService {
  def random: IO[UuidError, UUID]
  def parse(str: String): IO[UuidError, UUID]
}
//
object UuidService {
  def live: ZLayer[Any, Nothing, UuidService] =
    ZLayer.succeed(new UuidService {

      def random: IO[UuidError, UUID] =
        ZIO
          .fromTry(Try(UUID.randomUUID))
          .mapError(e => CannotCreate)

      def parse(str: String): IO[UuidError, UUID] =
        ZIO
          .fromTry(Try(UUID.fromString(str)))
          .mapError(e => CannotParse(cause = Some(e)))
    })
}
