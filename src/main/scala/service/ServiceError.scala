package ge.zgharbi.todocat
package service

import scala.reflect.*

trait ServiceError[A: ClassTag]:
  inline def service: String =
    classTag[A].runtimeClass.getSimpleName
