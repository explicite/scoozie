package org.scoozie.dsl.action

import org.scoozie.dsl.{OozieElement, Prepare, StringOption}
import org.scoozie.oozie.ACTIONType8
import org.scoozie.dsl._

trait Sqoop extends OozieElement[ACTIONType8] {
  protected val elementLabel = "sqoop"
  implicit override val canWriteXML: _root_.scalaxb.CanWriteXML[_root_.org.scoozie.oozie.ACTIONType8] =
    org.scoozie.oozie.Orgscoozieoozie_ACTIONType8Format
}

object Sqoop {
  def apply(
    actionOption: Option[StringOption],
    nameNode: Option[String],
    prepare: Prepare,
    launcher: Launcher,
    job: Option[String],
    configuration: Option[Configuration],
    options: Seq[StringOption],
    files: Seq[String],
    archives: Seq[String]
  ): Sqoop = new Sqoop {
    override protected val data = ACTIONType8(
      actiontype8option = actionOption,
      nameu45node = nameNode,
      prepare = Some(prepare.ooziePREPAREType2),
      launcher = Some(launcher.oozieLAUNCHERType2),
      jobu45xml = job.toSeq,
      configuration = configuration.map(_.oozieCONFIGURATIONType4),
      actiontype8option2 = options,
      file = files,
      archive = archives
    )
  }
}
