package ge.zgharbi.todocat
package services
import domain.UniId

import zio.*

import java.util.UUID

trait IdService {
  def make: IO[Throwable, UniId.IdType]
}

object IdService {
  def integer = ZLayer.succeed(new IdService {
    def make: IO[Throwable, UniId.IdType] =
      ZIO.succeed(1).map(UniId.toIdType)
  })

  def uuid = ZLayer.succeed(new IdService {
    def make: IO[Throwable, UniId.IdType] =
      ZIO.succeed(UUID.randomUUID()).map(UniId.toIdType)
  })
}
