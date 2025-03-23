package ge.zgharbi.todocat
package data

import effect.GenId
opaque type TaskItemId <: IdType = IdType

case class TaskItem(id: TaskItemId, title: String, description: String, isDone: Boolean)

object TaskItem {
  def apply(id: TaskItemId, title: String, description: String): TaskItem =
    new TaskItem(id, title, description, false)
}
