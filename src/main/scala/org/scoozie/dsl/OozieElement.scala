package org.scoozie.dsl

import scala.xml.{NamespaceBinding, NodeSeq}
import scalaxb.CanWriteXML

trait OozieElement[T] {
  protected val data: T
  protected val elementLabel: String
  implicit val canWriteXML: CanWriteXML[T]

  def toXML(scope: NamespaceBinding): NodeSeq = {
    scalaxb.toXML[T](data, elementLabel, scope)
  }
}
