package ge.zgharbi.todocat
package managers
import domain.UniId
import services.IdService

import zio.*

trait IdManager {
  def create[T <: UniId.IdType]: IO[Throwable, T]
}

object IdManager {
  def live = ZLayer
    .succeed(new IdManager {
      def create[T <: UniId.IdType]: IO[Throwable, T] =
        ZIO
          .service[IdService]
          .flatMap(_.make)
          .flatMap(UniId.fromIdType[T])
          .provideLayer(IdService.uuid)
    })
}
