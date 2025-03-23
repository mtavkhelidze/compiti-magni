package ge.zgharbi.todocat
package services

import data.{IdType, TaskItem, TaskItemId}
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
          genId.make[TaskItemId].map(id => TaskItem(id, title, description))
        }
      }
    }
}
