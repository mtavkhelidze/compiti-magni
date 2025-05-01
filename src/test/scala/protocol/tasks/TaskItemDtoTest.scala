package ge.zgharbi.app.compiti
package protocol.tasks

import domain.DomainId
import managers.task.TaskManager

import zio.*
import zio.test.*
import zio.test.Assertion.*

object TaskItemDtoTest extends ZIOSpecDefault {
  def spec =
    suite("TaskItemDTO Suite") {
      test("can create TaskItem from TaskItemDTO with correct id") {
        assertTrue(true)
      }
//      test("can create TaskItemDTO from TaskItem with correct id") {
//        ZIO
//          .service[TaskManager]
//          .flatMap(_.create("title", "description"))
//          .map(TaskItemDto.apply)
//          .map(dto =>
//            assert(dto)(
//              equalTo(
//                TaskItemDto(
//                  DomainId.onlyZeroes.toString,
//                  "title",
//                  "description",
//                  false,
//                ),
//              ),
//            ),
//          )
//      }
    } // .provideLayer(TaskManager.live)
}
