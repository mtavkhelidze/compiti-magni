package ge.zgharbi.todocat
package services

import data.TaskItem
import effect.GenId

import zio.*

trait TaskService {
  def create(title: String, description: String): Task[TaskItem]
}

object TaskService {
  def live: ZLayer[GenId, Nothing, TaskService] =
    ZLayer.fromFunction { (genId: GenId) =>
      new TaskService {
        def create(title: String, description: String): Task[TaskItem] = {
          genId.make[TaskItem.Id].map(id => 
            TaskItem(id, TaskItem.Title(title), TaskItem.Body(description))
          )
        }
      }
    }
}
