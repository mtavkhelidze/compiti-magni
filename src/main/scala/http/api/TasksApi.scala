package ge.zgharbi.todocat
package http.api

import managers.tasks.TasksManager
import protocol.tasks.*

class TasksApi(module: TasksManager) {
  val createEndpoint = (req: TasksCreateReq) =>
    module
      .create(req.title, req.description)
      .map(id => TasksCreateResp(id.toString))
      .mapError(e => e)
}
