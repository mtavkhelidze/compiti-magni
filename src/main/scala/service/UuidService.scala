package ge.zgharbi.todocat
package service

import algebra.DomainError

import zio.*

import java.util.UUID
import scala.util.Try

sealed trait UuidError[T] extends DomainError[T]
case class CannotCreate(
  override val message: String,
  override val cause: Option[Throwable],
) extends UuidError[CannotCreate]

case class InvalidString(
  override val message: String,
  override val cause: Option[Throwable],
) extends UuidError[InvalidString]

trait UuidService {
  def generate: IO[CannotCreate, UUID]
  def parse(str: String): IO[InvalidString, UUID]
}

object UuidService {
  def live: ZLayer[Any, Nothing, UuidService] =
    ZLayer.succeed(new UuidService {
      def generate: IO[CannotCreate, UUID] =
        ZIO
          .fromTry(Try(UUID.randomUUID))
          .mapError(e => CannotCreate("Cannot create UUID", Some(e)))
      def parse(str: String): IO[InvalidString, UUID] =
        ZIO
          .fromTry(Try(UUID.fromString(str)))
          .mapError(e => InvalidString("Invalid UUID string", Some(e)))
    })
}
