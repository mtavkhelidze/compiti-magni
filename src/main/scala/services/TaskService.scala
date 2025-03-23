package ge.zgharbi.todocat
package services

import data.{TaskItem, TaskItemId}
import effect.GenId

import zio.*

trait TaskService[F[_]] {
  def create(title: String, description: String): F[TaskItem]
}

object TaskService {
  def apply[F[_]](using taskService: TaskService[F]): TaskService[F] =
    taskService

  given TaskService[Task] with {
    def create(title: String, description: String): Task[TaskItem] =
      GenId[Task].make[TaskItemId].map(TaskItem(_, title, description))
  }
}
