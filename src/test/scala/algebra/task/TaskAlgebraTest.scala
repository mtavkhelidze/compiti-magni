package ge.zgharbi.todocat
package algebra.task

import algebra.DomainId

import zio.*
import zio.test.*
import zio.test.Assertion.*

object TaskAlgebraTest extends ZIOSpecDefault {
  def spec =
    suite("TaskAlgebra Suite")(
      test("create should generate TaskItem with correct ID") {
        ZIO
          .service[TasksManager]
          .flatMap(_.create("Test Task", "Test Description"))
          .map(taskItem =>
            assert(taskItem.id.toString)(equalTo(DomainId.onlyZeroes.toString)),
          )
      },
    ).provideLayer(DomainId.test >>> TasksManager.live)
}
