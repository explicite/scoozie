package org.scoozie.dsl.action

import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}
import org.scoozie.dsl.{CaptureOutput, StringOption}

class SshTest extends FlatSpec with GivenWhenThen with Matchers {

  behavior of "Ssh"

  it should "apply" in new fixture {
    Given("SSH object")
    val ssh = Ssh(
      host = host,
      command = command,
      options = Seq(args1, args2),
      captureOutput = captureOutput
    )

    When("XML is generated")
    val xmlGenerated = ssh.toXML(scope).toString()

    Then("is correct")
    xmlGenerated shouldBe sshInString

  }

  trait fixture {
    val scopeKey = "ssh"
    val scopeValue = "uri:oozie:ssh-action:0.2"
    val scope = scalaxb.toScope(Some(scopeKey) -> scopeValue)
    val host = "foo@bar.com"
    val command = "uploaddata"
    val args1 = StringOption("jdbc:derby://bar.com:1527/myDB")
    val args2 = StringOption("hdfs://foobar.com:9001/usr/tucu/myData")
    val captureOutput = CaptureOutput.Yes

    val sshInString =
      s"""<ssh xmlns:$scopeKey="$scopeValue"><ssh:host>$host</ssh:host><ssh:command>$command</ssh:command><args>jdbc:derby://bar.com:1527/myDB</args><args>hdfs://foobar.com:9001/usr/tucu/myData</args><ssh:capture-output/></ssh>"""
  }

}
