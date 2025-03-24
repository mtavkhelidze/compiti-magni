package ge.zgharbi.todocat
package dto.tasks

import zio.json.*

case class TaskCreateRequest(title: String, description: String)

object TaskCreateRequest:
  given JsonCodec[TaskCreateRequest] = DeriveJsonCodec.gen
