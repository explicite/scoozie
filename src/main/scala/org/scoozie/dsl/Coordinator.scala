package org.scoozie.dsl

import org.scoozie.oozie.{
  COMBINE, CONFIGURATIONType, CONTROLSType, COORDINATORu45APP, DATAIN, DATAOUT, DATASETS, INPUTEVENTS, INPUTLOGICOption,
  LOGICALANDOption, LOGICALDATAIN, LOGICALOR, OUTPUTEVENTS, PARAMETERSType, WORKFLOW
}
import scala.xml.XML

trait Coordinator extends COORDINATORu45APP {
  val oozieCoord: COORDINATORu45APP
  val xml: String =
    scalaxb
      .toXML[COORDINATORu45APP](oozieCoord, "oozie-coordinator", org.scoozie.oozie.defaultScope)
      .toString
}

object Coordinator {

  type Parameters = PARAMETERSType //TODO
  type Controls = CONTROLSType //TODO
  type Datasets = DATASETS //TODO
  type InputEvents = INPUTEVENTS //TODO
  type InputLogic = INPUTLOGICOption //TODO
  type AND = LOGICALANDOption //TODO
  type OR = LOGICALOR //TODO
  type Combine = COMBINE //TODO
  type LogicalDataIn = LOGICALDATAIN //TODO
  type DataIn = DATAIN //TODO
  type OutputEvents = OUTPUTEVENTS //TODO
  type DataOut = DATAOUT //TODO
  type Workflow = WORKFLOW //TODO
  type Configuration = CONFIGURATIONType //TODO

  def apply(oozieCoordinator: COORDINATORu45APP): Coordinator = {
    new Coordinator {
      override val oozieCoord = COORDINATORu45APP(
        parameters = ???,
        controls = ???,
        datasets = ???,
        inputu45events = ???,
        inputu45logic = ???,
        outputu45events = ???,
        action = ???
      )
    }
  }

  def unapply(string: String): Coordinator = {
    val xml = XML.loadString(string)
    val oozieCoordinator = scalaxb.fromXML[COORDINATORu45APP](xml)
    Coordinator(oozieCoordinator)
  }

}
