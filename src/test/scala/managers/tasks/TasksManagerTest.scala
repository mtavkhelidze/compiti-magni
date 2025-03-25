package ge.zgharbi.todocat
package managers.tasks

import effect.GenId

import zio.*
import zio.test.*
import zio.test.Assertion.*

object TasksManagerTest extends ZIOSpecDefault {
  def spec =
    suite("TaskService Suite")(
      test("create should generate TaskItem with correct ID") {
        ZIO
          .service[TasksManager]
          .flatMap(_.create("Test Task", "Test Description"))
          .map(taskItem =>
            assert(taskItem.id.toString)(equalTo(GenId.onlyZeroes.toString)),
          )
      },
    ).provideLayer(GenId.test >>> TasksManager.live)
}
