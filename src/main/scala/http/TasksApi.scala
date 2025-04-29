package ge.zgharbi.ms.id
package http

import managers.task.TaskManager
import protocol.tasks.*
import protocol.ApiErrorDto
import sttp.tapir.generic.auto.*
import sttp.tapir.ztapir.*
import sttp.tapir.PublicEndpoint
import sttp.tapir.json.zio.jsonBody

class TasksApi(module: TaskManager) {
  val createEp
    : PublicEndpoint[TasksCreateReq, ApiErrorDto, TaskItemDto, Any] = {
    endpoint.post
      .in("tasks")
      .in(jsonBody[TasksCreateReq])
      .errorOut(jsonBody[ApiErrorDto])
      .out(jsonBody[TaskItemDto])

  }
}
