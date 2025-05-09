package ge.zgharbi.compiti
package protocol.tasks

import zio.json.*

case class TasksCreateResp(id: String)

object TasksCreateResp {
  given JsonCodec[TasksCreateResp] = DeriveJsonCodec.gen
}
