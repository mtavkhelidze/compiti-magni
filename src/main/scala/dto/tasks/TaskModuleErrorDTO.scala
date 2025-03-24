package ge.zgharbi.todocat
package dto.tasks

import modules.tasks.TaskModuleError

import zio.json.*

case class TaskModuleErrorDTO(
  message: String,
  key: String,
  issues: List[String],
)

object TaskModuleErrorDTO {
  def apply[E <: TaskModuleError[?]](e: E): TaskModuleErrorDTO =
    TaskModuleErrorDTO(message = e.message, key = e.key, issues = e.issues)

  given JsonCodec[TaskModuleErrorDTO] = DeriveJsonCodec.gen
}
