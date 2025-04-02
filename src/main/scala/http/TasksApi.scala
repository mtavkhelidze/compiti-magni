package ge.zgharbi.todocat
package http

import protocol.tasks.*
import protocol.ApiErrorDto
import ge.zgharbi.todocat.managers.task.TasksManager

import sttp.tapir.generic.auto.*
import sttp.tapir.ztapir.*
import sttp.tapir.PublicEndpoint
import sttp.tapir.json.zio.jsonBody

class TasksApi(module: TasksManager) {
  val createEp: PublicEndpoint[TasksCreateReq, ApiErrorDto, TaskItemDto, Any] = {
    endpoint.post
      .in("tasks")
      .in(jsonBody[TasksCreateReq])
      .errorOut(jsonBody[ApiErrorDto])
      .out(jsonBody[TaskItemDto])

  }
}
