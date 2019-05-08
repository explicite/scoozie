lazy val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
lazy val scalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
lazy val dispatchV = "0.12.0"
lazy val dispatch = "net.databinder.dispatch" %% "dispatch-core" % dispatchV

lazy val root = (project in file(".")).
  enablePlugins(ScalaxbPlugin).
  settings(inThisBuild(List(
    organization  := "com.example",
    scalaVersion  := "2.12.3"
  ))).
  settings(
    name          := "scoozie",
    libraryDependencies ++= Seq(dispatch),
    libraryDependencies ++= {
      if (scalaVersion.value startsWith "2.10") Seq()
      else Seq(scalaXml, scalaParser)
    }).
  settings(
    scalaxbDispatchVersion in (Compile, scalaxb) := dispatchV,
    scalaxbPackageName in (Compile, scalaxb)     := "generated"
    // logLevel in (Compile, scalaxb) := Level.Debug
  )
