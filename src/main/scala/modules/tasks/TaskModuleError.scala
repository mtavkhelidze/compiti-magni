package ge.zgharbi.todocat
package modules.tasks

import zio.prelude.NonEmptyList

type TaskModuleError = NonEmptyList[TaskValidationError]

enum TaskValidationError:
  case TitleTooLong, DescriptionTooLong, TitleTooShort, DescriptionTooShort
