package org.scoozie

package object dsl {
  type ActionOption = scalaxb.DataRecord[String]
  type ActionFlag = org.scoozie.oozie.FLAGType
}
