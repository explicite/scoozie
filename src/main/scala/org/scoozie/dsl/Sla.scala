package org.scoozie.dsl

import org.scoozie.oozie.SLAu45INFOType

trait Sla extends OozieElement[SLAu45INFOType] {
  protected val elementLabel = "sla:info"
  implicit override val canWriteXML = org.scoozie.oozie.Orgscoozieoozie_SLAu45INFOTypeFormat
}

object Sla {

  def apply(
    nominalTime: String,
    shouldStart: Option[String] = None,
    shouldEnd: String,
    maxDuration: Option[String] = None,
    alertEvents: Option[String] = None,
    alertContact: Option[String] = None,
    notificationMessage: Option[String] = None,
    upstreamApps: Option[String] = None
  ): Sla = new Sla {
    val data = SLAu45INFOType(
      nominalu45time = nominalTime,
      shouldu45start = shouldStart,
      shouldu45end = shouldEnd,
      maxu45duration = maxDuration,
      alertu45events = alertEvents,
      alertu45contact = alertContact,
      notificationu45msg = notificationMessage,
      upstreamu45apps = upstreamApps
    )
  }

}
