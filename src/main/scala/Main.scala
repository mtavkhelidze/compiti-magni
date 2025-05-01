package ge.zgharbi.compiti

import domain.DomainError
import domain.DomainError.*

import zio.*

sealed trait SealedTraitError derives DomainError
case object SealedTraitErrorOne extends SealedTraitError
final case class SealedTraitErrorTwo(msg: String) extends SealedTraitError

object Main extends ZIOAppDefault {
//  private val appLayer =
//    IdManager.live >+> TaskManager.live

  override def run = {
    Console.printLine("Module: " + SealedTraitErrorOne.module) *>
      Console.printLine("Error : " + SealedTraitErrorOne.error)
//    ZIO
//      .service[TaskManager]
//      .flatMap(_.create("Test Task", "Test Description"))
//      .map(TaskItemDto.apply)
//      .map(_.toString)
//      .flatMap(t => ZIO.succeed(println(t)))
//      .provide(appLayer)
  }
}
