package ge.zgharbi.app.compiti
package services

import domain.{DomainError, UniId, WithMessage}

import zio.*

import java.util.UUID
import scala.util.Try

sealed trait IdServiceError extends WithMessage derives DomainError
case class InvalidIdTring(override val message: String) extends IdServiceError

trait IdService {
  def make[A: UniId]: IO[IdServiceError, A]
  def read[A: UniId](s: String): IO[IdServiceError, A]
}

object IdService {
  val uuid = ZLayer.fromFunction(() => new UuidBackend)
}

class UuidBackend extends IdService {
  override def make[A: UniId]: IO[IdServiceError, A] =
    ZIO
      .fromTry(Try(UUID.randomUUID))
      .mapError(_ => InvalidIdTring(s"Invalid UUID"))
      .map(UniId[A].id.get)

  override def read[A: UniId](s: String): IO[IdServiceError, A] =
    ZIO
      .fromTry(Try(UUID.fromString(s)))
      .mapError(_ => InvalidIdTring(s"Invalid string: $s"))
      .map(UniId[A].id.get)
}
