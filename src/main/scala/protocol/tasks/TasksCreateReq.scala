package ge.zgharbi.app.compiti
package protocol.tasks

import zio.json.*

case class TasksCreateReq(title: String, description: String)

object TasksCreateReq:
  given JsonCodec[TasksCreateReq] = DeriveJsonCodec.gen
