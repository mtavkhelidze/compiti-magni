package ge.zgharbi.todocat
package lib.validation

import data.TaskItem

def validateTitle(s: String) =
  isLongerThan(3)(s).flatMap(isShorterThan(100)).as(TaskItem.Title)
