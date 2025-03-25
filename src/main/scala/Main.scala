package ge.zgharbi.todocat

import effect.GenId
import managers.tasks.TasksManager

import zio.*

object Main extends ZIOAppDefault {
  private val appLayer: ZLayer[Any, Nothing, TasksManager] =
    GenId.live >>> TasksManager.live

  override def run: ZIO[Any, Throwable, Unit] =
    ZIO
      .serviceWithZIO[TasksManager](_.create("t1", "d1"))
      .flatMap(Console.printLine(_))
      .provideLayer(appLayer)
}
