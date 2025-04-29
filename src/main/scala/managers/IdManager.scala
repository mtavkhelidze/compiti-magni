package ge.zgharbi.ms.id
package managers

import domain.{DomainError, UniId}
import services.IdService

import zio.*

trait IdManager {
  def create[T : UniId]: IO[Throwable, T]
}

object IdManager {
//  def live: TaskLayer[IdManager] = ZLayer
//    .succeed(new IdManager {
//      def create[T : UniId]: IO[Throwable, T] =
//        ZIO
//          .service[IdService]
//          .flatMap(_.make)
//          .provideLayer(IdService.test)
//    })
}
