package org.scoozie.dsl.action

import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

class EmailTest extends FlatSpec with GivenWhenThen with Matchers {

  behavior of "Email"

  it should "apply" in new fixture {
    Given("Email object")
    val email = Email(
      to = to,
      cc = Some(cc),
      bcc = Some(bcc),
      subject = subject,
      body = body,
      contentType = Some(contentType),
      attachment = Some(attachment)
    )

    When("XML is generate")
    val xmlGenerated = email.toXML(scope).toString()

    Then("is correct")
    xmlGenerated shouldBe emailInString
  }

  trait fixture {
    val scopeKey = "email"
    val scopeValue = "uri:oozie:email-action:0.2"
    val scope = scalaxb.toScope(Some(scopeKey) -> scopeValue)
    val to = "to@bar.com"
    val cc = "cc@bar.com"
    val bcc = "bcc@bar.com"
    val subject = "email subject"
    val body = "email body"
    val contentType = "content type"
    val attachment = "attachment"
    val emailInString = s"""<email xmlns:$scopeKey="$scopeValue"><email:to>$to</email:to><email:cc>$cc</email:cc><email:bcc>$bcc</email:bcc><email:subject>$subject</email:subject><email:body>$body</email:body><email:content_type>$contentType</email:content_type><email:attachment>$attachment</email:attachment></email>""".mkString
  }

}
