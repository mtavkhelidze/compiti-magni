package ge.zgharbi.todocat
package dto

import zio.json.*

case class TaskListResponse(tasks: List[TaskItemDTO])

object TaskListResponse:
  given JsonCodec[TaskListResponse] = DeriveJsonCodec.gen
