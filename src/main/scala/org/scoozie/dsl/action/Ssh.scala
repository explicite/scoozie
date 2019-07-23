package org.scoozie.dsl.action

import org.scoozie.dsl.OozieElement
import org.scoozie.oozie.ACTIONType7
import org.scoozie.dsl._

trait Ssh extends OozieElement[ACTIONType7] {
  protected val elementLabel = "ssh"
  implicit override val canWriteXML = org.scoozie.oozie.Orgscoozieoozie_ACTIONType7Format
}

object Ssh {
  def apply(
    host: String,
    command: String,
    options: Seq[StringOption],
    captureOutput: CaptureOutput
  ): Ssh = new Ssh {
    override protected val data = ACTIONType7(
      host = host,
      command = command,
      actiontype7option = options,
      captureu45output = captureOutput.value
    )
  }
}
