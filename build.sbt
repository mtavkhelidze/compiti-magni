val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "CompitiMagni",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    idePackagePrefix := Some("ge.zgharbi.todocat"),
    testFrameworks += new TestFramework("weaver.framework.CatsEffect"),
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % "1.11.25",
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % "1.11.25",
      "dev.zio" %% "zio" % "2.1.17",
      "dev.zio" %% "zio-http" % "3.2.0",
      "dev.zio" %% "zio-json" % "0.7.42",
      "dev.zio" %% "zio-logging" % "2.5.0",
      "dev.zio" %% "zio-logging-slf4j" % "2.5.0",
      "dev.zio" %% "zio-prelude" % "1.0.0-RC39",
      "dev.zio" %% "zio-test" % "2.1.17" % Test,
      "ch.qos.logback" % "logback-classic" % "1.5.18",
      "dev.zio" %% "zio-test-sbt" % "2.1.17" % Test,
    ),
    scalacOptions ++= Seq(
      "-Xfatal-warnings", // Treat warnings as errors
      "-deprecation", // Show deprecation warnings
      // "-explain", // Explain errors in detail
      "-feature", // Show feature warnings
      "-unchecked", // Enable extra runtime checks
    ),
  )

Compile / run / fork := true
Global / excludeLintKeys ++= Set(idePackagePrefix, scalaVersion, version)
Global / onChangedBuildSource := ReloadOnSourceChanges
