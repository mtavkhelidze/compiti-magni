package ge.zgharbi.todocat
package protocol

import algebra.task.TitleValidationError
import algebra.DomainError

import zio.http.Status
import zio.json.*

import scala.compiletime.constValue

case class ApiErrorDto(
  code: Int,
  message: String,
  key: String,
  issues: List[String],
)

object ApiErrorDto {
  extension [E <: DomainError[E]](e: E) {
    inline def statusCode: Status = e match {
      case _: TitleValidationError => Status.Created
      case _                       => Status.fromInt(400)
    }
  }
  inline def apply[E <: DomainError[E]](e: E): ApiErrorDto = {
    ApiErrorDto(
      code = e.statusCode.code,
      message = e.message,
      key = e.key.toString,
      issues = e.issues,
    )
  }

  given JsonCodec[ApiErrorDto] = DeriveJsonCodec.gen
}
