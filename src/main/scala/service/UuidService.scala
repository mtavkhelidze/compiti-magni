package ge.zgharbi.todocat
package service

trait UuidError extends ServiceError[UuidError]

case class Misha() extends UuidError

//sealed trait UuidError extends ServiceError[UuidError]
//
//case object CannotCreate extends UuidError
//case class InvalidString(override val cause: Option[Throwable] = None)
//    extends UuidError
//
//trait UuidService {
//  def generate: IO[UuidError, UUID]
//  def parse(str: String): IO[UuidError, UUID]
//}
//
//object UuidService {
//  def live: ZLayer[Any, Nothing, UuidService] =
//    ZLayer.succeed(new UuidService {
//      def generate: IO[UuidError, UUID] =
//        ZIO
//          .fromTry(Try(UUID.randomUUID))
//          .mapError(e => CannotCreate)
//      def parse(str: String): IO[InvalidString, UUID] =
//        ZIO
//          .fromTry(Try(UUID.fromString(str)))
//          .mapError(e => InvalidString(cause = Some(e)))
//    })
//}
