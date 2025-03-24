package ge.zgharbi.todocat
package modules.tasks

import data.TaskItem

import zio.*

trait TaskModule {
  def create(
    title: String,
    description: String,
  ): IO[BodyValidationError | TitleValidationError, TaskItem.Id]
}

object TaskModule {}
