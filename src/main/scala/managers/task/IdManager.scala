package ge.zgharbi.app.compiti
package managers.task

import managers.IdManager

import zio.*

trait IdManager {
  def uuid(
    title: String,
    description: String,
  ): ZIO[IdManager, Throwable, TaskItem]
}

object IdManager {
//  def live: ZLayer[IdManager, Throwable, TaskManager] =
//    ZLayer.succeed(new TaskManager {
//      def create(title: String, description: String) =
//        ZIO
//          .serviceWithZIO[IdManager](_.create)
//          .map(
//            TaskItem
//              .apply(_, TaskItem.Title(title), TaskItem.Body(description)),
//          )
//    })
}
