package ge.zgharbi.todocat

import effect.GenId
import services.TaskService

import zio.*

object Main extends ZIOAppDefault {
  private val appLayer: ZLayer[Any, Nothing, TaskService] =
    GenId.live >>> TaskService.live

  override def run: ZIO[Any, Throwable, Unit] =
    ZIO
      .serviceWithZIO[TaskService](_.create("t1", "d1"))
      .flatMap(Console.printLine(_))
      .provideLayer(appLayer)
}
