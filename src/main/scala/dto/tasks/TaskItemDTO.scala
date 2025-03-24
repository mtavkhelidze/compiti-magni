package ge.zgharbi.todocat
package dto.tasks

import data.TaskItem

import zio.json.*

case class TaskItemDTO(
  id: String,
  title: String,
  description: String,
  isDone: Boolean,
)

object TaskItemDTO {
  import TaskItem.*

  given JsonCodec[TaskItemDTO] = DeriveJsonCodec.gen

  def apply(ti: TaskItem): TaskItemDTO =
    TaskItemDTO(
      id = ti.id.toString,
      title = ti.title,
      description = ti.description,
      isDone = ti.isDone,
    )
}
