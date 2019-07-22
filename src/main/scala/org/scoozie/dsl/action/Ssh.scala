package org.scoozie.dsl.action

import org.scoozie.dsl.DSLElem
import org.scoozie.oozie.ACTIONType7
import org.scoozie.dsl._

trait Ssh extends DSLElem[ACTIONType7] {
  protected val elementLabel = "ssh"
  implicit override val canWriteXML = org.scoozie.oozie.Orgscoozieoozie_ACTIONType7Format
}

object Ssh {

  def apply(
    host: String,
    command: String,
    options: Seq[ActionOption],
    captureOutput: ActionFlag
  ): Ssh = new Ssh {
    override protected val data = ACTIONType7(
      host = host,
      command = command,
      actiontype7option = options,
      captureu45output = Option(captureOutput)
    )
  }
}
