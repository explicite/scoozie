package org.scoozie
import oozie._

package object dsl {

  type ActionOption = scalaxb.DataRecord[String]
  object ActionOption {
    def apply(value: String): ActionOption = {
      scalaxb.DataRecord(None, Some("args"), value)
    }
  }

  type ActionFlag = org.scoozie.oozie.FLAGType

  case class CaptureOutput(value: Option[ActionFlag])

  object CaptureOutput {
    val Yes = CaptureOutput(Some(new ActionFlag))
    val No = CaptureOutput(None)
  }
}
