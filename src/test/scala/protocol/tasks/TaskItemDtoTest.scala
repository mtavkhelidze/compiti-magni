package ge.zgharbi.todocat
package protocol.tasks

import ge.zgharbi.todocat.algebra.task.TasksManager
import ge.zgharbi.todocat.algebra.DomainId

import zio.*
import zio.test.*
import zio.test.Assertion.*

object TaskItemDtoTest extends ZIOSpecDefault {
  def spec =
    suite("TaskItemDTO Suite") {
      test("can create TaskItemDTO from TaskItem with correct id") {
        ZIO
          .service[TasksManager]
          .flatMap(_.create("title", "description"))
          .map(TaskItemDto.apply)
          .map(dto =>
            assert(dto)(
              equalTo(
                TaskItemDto(
                  DomainId.onlyZeroes.toString,
                  "title",
                  "description",
                  false,
                ),
              ),
            ),
          )
      }
    }.provideLayer(DomainId.test >>> TasksManager.live)
}
