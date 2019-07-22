package org.scoozie.dsl.action

import org.scoozie.dsl.{DSLElem, Prepare, StringOption}
import org.scoozie.oozie.ACTIONType8
import org.scoozie.dsl._

trait Sqoop extends DSLElem[ACTIONType8] {
  protected val elementLabel = "sqoop"
  implicit override val canWriteXML: _root_.scalaxb.CanWriteXML[_root_.org.scoozie.oozie.ACTIONType8] =
    org.scoozie.oozie.Orgscoozieoozie_ACTIONType8Format
}

object Sqoop {
  def apply(
    actionOption: Option[StringOption],
    nameNode: Option[String],
    prepare: Prepare,
    launcher: Launcher
  ): Sqoop = new Sqoop {
    override protected val data = ACTIONType8(
      actionOption,
      nameNode,
      Some(prepare.ooziePREPAREType2),
      Some(launcher.oozieLAUNCHERType2)
    )
  }
}
