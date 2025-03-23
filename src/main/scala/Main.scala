package ge.zgharbi.todocat

import services.TaskService

import zio.*

object Main extends ZIOAppDefault {
  override def run: ZIO[Any, Throwable, Unit] = {
    for {
      t1 <- TaskService[Task].create("t1", "d1")
      _ <- Console.printLine(t1)
    } yield ()
  }
}
