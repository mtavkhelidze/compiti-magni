package ge.zgharbi.todocat
package services

import effect.GenId

import zio.*
import zio.test.*
import zio.test.Assertion.*

object TaskServiceTest extends ZIOSpecDefault {
  def spec =
    suite("TaskService Suite")(
      test("create should generate TaskItem with correct ID") {
        ZIO
          .service[TaskService]
          .flatMap(_.create("Test Task", "Test Description"))
          .map(taskItem =>
            assert(taskItem.id.toString)(equalTo(GenId.onlyZeroes.toString)),
          )
      },
    ).provideLayer(GenId.test >>> TaskService.live)
}
