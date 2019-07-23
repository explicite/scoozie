package org.scoozie.dsl.action

import org.scoozie.dsl.OozieElement
import org.scoozie.oozie.ACTIONType

trait Email extends OozieElement[ACTIONType] {
  protected val elementLabel = "email"
  implicit override val canWriteXML = org.scoozie.oozie.Orgscoozieoozie_ACTIONTypeFormat
}

object Email {
  def apply(
    to: String,
    cc: Option[String] = None,
    bcc: Option[String] = None,
    subject: String,
    body: String,
    contentType: Option[String] = None,
    attachment: Option[String] = None
  ): Email = new Email {
    override protected val data = ACTIONType(
      to = to,
      cc = cc,
      bcc = bcc,
      subject = subject,
      body = body,
      content_type = contentType,
      attachment = attachment
    )
  }
}
