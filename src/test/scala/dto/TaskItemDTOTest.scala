package ge.zgharbi.todocat
package dto

import effect.GenId
import services.TaskService

import zio.*
import zio.test.*
import zio.test.Assertion.*

object TaskItemDTOTest extends ZIOSpecDefault {
  def spec =
    suite("TaskItemDTO Suite") {
      test("can create TaskItemDTO from TaskItem with correct id") {
        ZIO
          .service[TaskService]
          .flatMap(_.create("title", "description"))
          .map(TaskItemDTO.apply)
          .map(dto =>
            assert(dto)(
              equalTo(
                TaskItemDTO(
                  GenId.onlyZeroes.toString,
                  "title",
                  "description",
                  false,
                ),
              ),
            ),
          )
      }
    }.provideLayer(GenId.test >>> TaskService.live)
}
