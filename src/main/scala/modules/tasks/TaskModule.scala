package ge.zgharbi.todocat
package modules.tasks

import data.TaskItem
import lib.validation.*

import zio.*
import zio.prelude.*

trait TaskModule {
  def create(
    title: String,
    description: String,
  ): IO[TaskModuleError, TaskItem.Id]
}

object TaskModule {
  private def validateTitle(s: String): Validation[String, String] =
    Validation
      .validate(isLongerThan(3)(s), isShorterThan(100)(s))
      .map(_ => s)
}
