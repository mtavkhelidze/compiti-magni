package ge.zgharbi.todocat
package protocol

import ge.zgharbi.todocat.managers.tasks.{BodyValidationError, TitleValidationError}

import zio.*
import zio.http.Status
import zio.test.*
import zio.test.Assertion.*

object ApiErrorDtoTest extends ZIOSpecDefault {

  // Gen for List[String] (validation issues)
  val issuesGen: Gen[Any, List[String]] = Gen.listOf(Gen.alphaNumericString)

  // Gen for TitleValidationError
  val titleValidationErrorGen: Gen[Any, TitleValidationError] =
    for {
      message <- Gen.alphaNumericString
      issues <- issuesGen
    } yield TitleValidationError(message, issues)

  // Gen for BodyValidationError
  val bodyValidationErrorGen: Gen[Any, BodyValidationError] =
    for {
      message <- Gen.alphaNumericString
      issues <- issuesGen
    } yield BodyValidationError(message, issues)
//
//  // Gen for TaskCreationError
//  val taskCreationErrorGen: Gen[Any, TaskCreationError] =
//    for {
//      message <- Gen.alphaNumericString
//    } yield TaskCreationError(message)

  // Combine the above gens into a Gen for TaskModuleError
  val taskModuleErrorGen =
    Gen.oneOf(
      titleValidationErrorGen,
      bodyValidationErrorGen,
//      taskCreationErrorGen,
    )

  // Test to check TaskModuleErrorDTO conversion
  def spec =
    suite("TaskModuleErrorDTO Test")(
      test("should convert TitleValidationError to TaskModuleErrorDTO") {
        check(titleValidationErrorGen) { error =>
          val dto = ApiErrorDto(error)
          assert(dto.code)(equalTo(Status.Created.code)) &&
          assert(dto.message)(equalTo(error.message)) &&
          assert(dto.key)(equalTo("TitleValidationError")) &&
          assert(dto.issues)(equalTo(error.issues))
        }
      },
      test("should convert BodyValidationError to TaskModuleErrorDTO") {
        check(bodyValidationErrorGen) { error =>
          val dto = ApiErrorDto(error)
          assert(dto.message)(equalTo(error.message)) &&
          assert(dto.key)(equalTo("BodyValidationError")) &&
          assert(dto.issues)(equalTo(error.issues))
        }
      },
//      test("should convert TaskCreationError to TaskModuleErrorDTO") {
//        check(taskCreationErrorGen) { error =>
//          val dto = TaskModuleErrorDTO(error)
//          assert(dto.message)(equalTo(error.message)) &&
//          assert(dto.key)(equalTo("TaskCreationError")) &&
//          assert(dto.issues)(equalTo(List.empty))
//        }
//      },
    )
}
