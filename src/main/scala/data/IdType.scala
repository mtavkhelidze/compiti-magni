package ge.zgharbi.todocat
package data

import java.util.UUID

opaque type IdType = UUID
object IdType {
  def make: IdType = UUID.randomUUID
  def read(str: String): IdType = UUID.fromString(str)
}
