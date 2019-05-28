resolvers ++= Seq(
  Resolver.sonatypeRepo("public")
)

addSbtPlugin("org.scalaxb" % "sbt-scalaxb" % "1.5.2")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.0")
