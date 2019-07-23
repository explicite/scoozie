package org.scoozie
import oozie._

package object dsl {

  type StringOption = scalaxb.DataRecord[String]
  object StringOption {
    def apply(value: String): StringOption = {
      scalaxb.DataRecord(None, Some("args"), value)
    }
  }

  type ActionFlag = org.scoozie.oozie.FLAGType

  case class CaptureOutput(value: Option[ActionFlag])

  object CaptureOutput {
    val Yes = CaptureOutput(Some(new ActionFlag))
    val No = CaptureOutput(None)
  }

  implicit class Prepare(prepare: PREPAREType2) {
    def delete(path: String): Prepare = {
      prepare.copy(prepare.delete :+ DELETEType2(Map("@path" -> StringOption(path))), prepare.mkdir)
    }

    def mkdir(path: String): Prepare = {
      prepare.copy(prepare.delete, prepare.mkdir :+ MKDIRType2(Map("@path" -> StringOption(path))))
    }
    val ooziePREPAREType2 = prepare
  }

  object Prepare {
    def delete(path: String): Prepare = {
      PREPAREType2(Seq(DELETEType2(Map("@path" -> StringOption(path)))), Seq.empty[MKDIRType2])
    }

    def mkdir(path: String): Prepare = {
      PREPAREType2(Seq.empty[DELETEType2], Seq(MKDIRType2(Map("@path" -> StringOption(path)))))
    }
  }

  implicit class Launcher(launcher: LAUNCHERType2) {
    val oozieLAUNCHERType2 = launcher
  }

  object Launcher {
    def apply(
      memoryMb: Option[Int],
      vCores: Option[Int],
      javaOpts: Option[String],
      env: Option[String],
      queue: Option[String],
      sharedLib: Option[String],
      viewAcl: Option[String],
      modifyAcl: Option[String]
    ): Launcher = {
      LAUNCHERType2(
        Seq(
          memoryMb.map(scalaxb.DataRecord(None, Some("memory.mb"), _)),
          vCores.map(scalaxb.DataRecord(None, Some("vcores"), _)),
          javaOpts.map(scalaxb.DataRecord(None, Some("java-opts"), _)),
          env.map(scalaxb.DataRecord(None, Some("env"), _)),
          queue.map(scalaxb.DataRecord(None, Some("queue"), _)),
          sharedLib.map(scalaxb.DataRecord(None, Some("sharedlib"), _)),
          viewAcl.map(scalaxb.DataRecord(None, Some("view-acl"), _)),
          modifyAcl.map(scalaxb.DataRecord(None, Some("modify-acl"), _))
        ).flatten
      )

    }
  }

  implicit class Configuration(configuration: CONFIGURATIONType4) {
    val oozieCONFIGURATIONType4 = configuration
    def add(name: String, value: String, description: Option[String] = None): Configuration = {
      oozieCONFIGURATIONType4.copy(property = oozieCONFIGURATIONType4.property :+ Property9(name, value, description))
    }
  }

  object Configuration {
    def apply(name: String, value: String, description: Option[String] = None): Configuration = {
      CONFIGURATIONType4(Seq(Property9(name, value, description)))
    }
  }

}
