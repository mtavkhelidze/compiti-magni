package ge.zgharbi.todocat
package protocol.tasks

import effect.GenId
import managers.tasks.TasksManager

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
                  GenId.onlyZeroes.toString,
                  "title",
                  "description",
                  false,
                ),
              ),
            ),
          )
      }
    }.provideLayer(GenId.test >>> TasksManager.live)
}
