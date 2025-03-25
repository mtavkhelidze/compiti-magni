package ge.zgharbi.todocat
package managers.tasks


import data.TaskItem
import effect.GenId

import zio.*

trait TasksManager {
  def create(title: String, description: String): Task[TaskItem]
}

object TasksManager {
  def live: ZLayer[GenId, Nothing, TasksManager] =
    ZLayer.fromFunction { (genId: GenId) =>
      new TasksManager {
        def create(title: String, description: String): Task[TaskItem] = {
          genId.make[TaskItem.Id].map(id => 
            TaskItem(id, TaskItem.Title(title), TaskItem.Body(description))
          )
        }
      }
    }
}
