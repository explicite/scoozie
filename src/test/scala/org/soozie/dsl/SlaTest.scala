package org.soozie.dsl

import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}
import org.scoozie.dsl.Sla

class SlaTest extends FlatSpec with GivenWhenThen with Matchers {

  "Sla" should "generate correct xml" in new Fixture {
    Given("SLA object")
    val sla = Sla(
      nominalTime = nominalTime,
      shouldStart = Some(shouldStart),
      shouldEnd = shouldEnd,
      maxDuration = Some(maxDuration),
      alertEvents = Some(alertEvents),
      alertContact = Some(alertContact)
    )

    When("XML is generate")
    val xmlGenerated = sla.toXML(scope).toString()

    Then("is correct")
    xmlGenerated shouldBe slaInString
  }


  trait Fixture {
    val scopeKey = "sla"
    val scopeValue = "uri:oozie:sla:0.2"
    val scope = scalaxb.toScope(Some(scopeKey) -> scopeValue)
    val nominalTime  = "${nominal_time}"
    val shouldStart = "${10 * MINUTES}"
    val shouldEnd = "${30 * MINUTES}"
    val maxDuration = "${30 * MINUTES}"
    val alertEvents = "start_miss,end_miss,duration_miss"
    val alertContact = """joe@example.com"""
    val slaInString =s"""<sla:info xmlns:$scopeKey="$scopeValue"><sla:nominal-time>$nominalTime</sla:nominal-time><sla:should-start>$shouldStart</sla:should-start><sla:should-end>$shouldEnd</sla:should-end><sla:max-duration>$maxDuration</sla:max-duration><sla:alert-events>$alertEvents</sla:alert-events><sla:alert-contact>$alertContact</sla:alert-contact></sla:info>""".mkString
  }
}
