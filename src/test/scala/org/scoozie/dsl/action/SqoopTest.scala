package org.scoozie.dsl.action

import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}
import org.scoozie.dsl.{Configuration, Launcher, Prepare, StringOption}

class SqoopTest extends FlatSpec with GivenWhenThen with Matchers {

  behavior of "Sqoop"

  it should "apply" in new fixture {
    Given("Sqoop object")
    val sqoop = Sqoop(
      Some(actionOption),
      Some(nameNode),
      prepare,
      launcher,
      Some(job),
      Some(configuration),
      Seq(StringOption(options)),
      Seq(files),
      Seq(archives)
    )

    When("XML is generated")
    val xmlGenerated = sqoop.toXML(scope).toString()

    Then("is correct")
    xmlGenerated shouldBe sqoopInString
  }

  trait fixture {
    val scopeKey = "sqoop"
    val scopeValue = "uri:oozie:sqoop-action:0.2"
    val scope = scalaxb.toScope(Some(scopeKey) -> scopeValue)
    val option = "option"
    val actionOption = StringOption(option)
    val nameNode = "nameNode"
    val prepareDelete = "prepare/delete"
    val prepareMkdir = "prepare/mkdir"
    val prepare = Prepare mkdir prepareMkdir delete prepareDelete
    val memoryMb = 128
    val vCores = 4
    val javaOpts = "xmx:128"
    val env = "ECHO"
    val queue = "default"
    val sharedLib = "some_lib.jar"
    val viewAcl = "view_acl"
    val modifyAcl = "modify_acl"
    val launcher = Launcher(
      memoryMb = Some(memoryMb),
      vCores = Some(vCores),
      javaOpts = Some(javaOpts),
      env = Some(env),
      queue = Some(queue),
      sharedLib = Some(sharedLib),
      viewAcl = Some(viewAcl),
      modifyAcl = Some(modifyAcl)
    )
    val job = "job"
    val confName1 = "confName1"
    val confValue1 = "confValue1"
    val confDesc1 = "confDesc1"
    val confName2 = "confName2"
    val confValue2 = "confValue2"
    val confDesc2 = "confDesc2"
    val configuration =
      Configuration(confName1, confValue1, Some(confDesc1)).add(confName2, confValue2, Some(confDesc2))
    val options = "options"
    val files = "files"
    val archives = "archives"
    val sqoopInString =
      s"""<sqoop xmlns:sqoop="uri:oozie:sqoop-action:0.2"><args>$option</args><name-node>$nameNode</name-node><prepare><delete path="$prepareDelete"/><mkdir path="$prepareMkdir"/></prepare><launcher><memory.mb>$memoryMb</memory.mb><vcores>$vCores</vcores><java-opts>$javaOpts</java-opts><env>$env</env><queue>$queue</queue><sharedlib>$sharedLib</sharedlib><view-acl>$viewAcl</view-acl><modify-acl>$modifyAcl</modify-acl></launcher><job-xml>$job</job-xml><configuration><property><name>$confName1</name><value>$confValue1</value><description>$confDesc1</description></property><property><name>$confName2</name><value>$confValue2</value><description>$confDesc2</description></property></configuration><args>$options</args><file>$files</file><archive>$archives</archive></sqoop>""".mkString
  }

}
