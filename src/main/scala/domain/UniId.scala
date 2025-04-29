package ge.zgharbi.ms.id
package domain
import java.util.UUID

case class Iso[A, B](get: A => B, reverse: B => A)
object Iso {
  def apply[A, B](get: A => B, reverse: B => A): Iso[A, B] =
    new Iso(get, reverse)
}

trait UniId[A] {
  def id: Iso[UUID, A]
}

object UniId {
  def apply[A](using UniId[A]): UniId[A] = summon[UniId[A]]

  given uuid: UniId[UUID] with {
    def id: Iso[UUID, UUID] = Iso(identity, identity)
  }
}
