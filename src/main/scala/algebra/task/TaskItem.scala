package ge.zgharbi.todocat
package algebra.task

import algebra.task.TaskItem.*
import ge.zgharbi.todocat.domain.DomainIdType

case class TaskItem(id: Id, title: Title, description: Body, isDone: Boolean)

object TaskItem {
  opaque type Id <: DomainIdType = DomainIdType
  opaque type Title <: String = String
  opaque type Body <: String = String

  def apply(id: TaskItem.Id, title: Title, body: Body): TaskItem =
    new TaskItem(id, title, body, false)

  object Title {
    def apply(title: String): Title = title
  }

  object Body {
    def apply(body: String): Body = body
  }
}
