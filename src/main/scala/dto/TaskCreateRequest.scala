package ge.zgharbi.todocat
package dto

import zio.json.*

case class TaskCreateRequest(title: String, description: String)

object TaskCreateRequest:
  given JsonCodec[TaskCreateRequest] = DeriveJsonCodec.gen
