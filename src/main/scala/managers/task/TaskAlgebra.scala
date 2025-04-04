package ge.zgharbi.todocat
package managers.task

import domain.DomainId

import zio.*

trait TasksManager {
  def create(title: String, description: String): Task[TaskItem]
}

object TasksManager {
  def live: ZLayer[DomainId, Nothing, TasksManager] =
    ZLayer.fromFunction { (genId: DomainId) =>
      new TasksManager {
        def create(title: String, description: String): Task[TaskItem] = {
          genId
            .make[TaskItem.Id]
            .map(id =>
              TaskItem(id, TaskItem.Title(title), TaskItem.Body(description)),
            )
        }
      }
    }
}
