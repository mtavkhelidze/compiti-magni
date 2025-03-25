package ge.zgharbi.todocat

import ge.zgharbi.todocat.algebra.task.TasksManager
import ge.zgharbi.todocat.algebra.DomainId

import zio.*

object Main extends ZIOAppDefault {
  private val appLayer: ZLayer[Any, Nothing, TasksManager] =
    DomainId.live >>> TasksManager.live

  override def run: ZIO[Any, Throwable, Unit] =
    ZIO
      .serviceWithZIO[TasksManager](_.create("t1", "d1"))
      .flatMap(Console.printLine(_))
      .provideLayer(appLayer)
}
