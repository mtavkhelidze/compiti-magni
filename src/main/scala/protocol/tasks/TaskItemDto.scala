package ge.zgharbi.compiti
package protocol.tasks

import managers.task.TaskItem
import zio.json.*

case class TaskItemDto(
  id: String,
  title: String,
  description: String,
  isDone: Boolean,
)

object TaskItemDto {
  import TaskItem.*

  given JsonCodec[TaskItemDto] = DeriveJsonCodec.gen

  def apply(ti: TaskItem): TaskItemDto =
    TaskItemDto(
      id = ti.id.toString,
      title = ti.title,
      description = ti.description,
      isDone = ti.isDone,
    )
}
