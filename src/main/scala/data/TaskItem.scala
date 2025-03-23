package ge.zgharbi.todocat
package data

import TaskItem.*

case class TaskItem(
  id: Id,
  title: Title,
  description: Body,
  isDone: Boolean,
)

object TaskItem {
  opaque type Id <: IdType = IdType
  opaque type Title <: String = String
  object Title {
    def apply(title: String): Title = title
  }
  opaque type Body <: String = String
  object Body {
    def apply(body: String): Body = body
  }

  def apply(id: TaskItem.Id, title: Title, body: Body): TaskItem =
    new TaskItem(id, title, body, false)
}
