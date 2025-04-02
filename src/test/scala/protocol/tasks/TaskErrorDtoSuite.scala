package ge.zgharbi.todocat
package protocol.tasks

import managers.task.*
import protocol.ApiErrorDto

import zio.*
import zio.http.Status
import zio.test.*
import zio.test.Assertion.*

object TaskErrorDtoSuite extends ZIOSpecDefault {
  val issuesGen: Gen[Any, List[String]] = Gen.listOf(Gen.alphaNumericString)
  val titleValidationErrorGen: Gen[Any, TitleValidationError] =
    for {
      message <- Gen.alphaNumericString
      issues <- issuesGen
    } yield TitleValidationError(message, issues)

  val bodyValidationErrorGen: Gen[Any, BodyValidationError] =
    for {
      message <- Gen.alphaNumericString
      issues <- issuesGen
    } yield BodyValidationError(message, issues)

  val taskErrorGen =
    Gen.oneOf(titleValidationErrorGen, bodyValidationErrorGen)

  def spec =
    suite("TaskErrorDto Suite")(
      test("should convert TitleValidationError to ApiErrorDTO") {
        check(titleValidationErrorGen) { error =>
          val dto = ApiErrorDto(error)
          assert(dto.code)(equalTo(Status.Created.code)) &&
          assert(dto.message)(equalTo(error.message)) &&
          assert(dto.key)(equalTo("TitleValidationError")) &&
          assert(dto.issues)(equalTo(error.issues))
        }
      },
      test("should convert BodyValidationError to ApiErrorDTO") {
        check(bodyValidationErrorGen) { error =>
          val dto = ApiErrorDto(error)
          assert(dto.message)(equalTo(error.message)) &&
          assert(dto.key)(equalTo("BodyValidationError")) &&
          assert(dto.issues)(equalTo(error.issues))
        }
      },
    )
}
