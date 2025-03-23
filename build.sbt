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
      "ch.qos.logback" % "logback-classic" % "1.5.18",
      "dev.zio" %% "zio" % "2.1.16",
    ),
    scalacOptions ++= Seq(
      "-Xfatal-warnings", // Treat warnings as errors
      "-deprecation", // Show deprecation warnings
      "-explain", // Explain errors in detail
      "-feature", // Show feature warnings
      "-unchecked", // Enable extra runtime checks
    ),
  )

Compile / run / fork := true
Global / excludeLintKeys ++= Set(idePackagePrefix, scalaVersion, version)
Global / onChangedBuildSource := ReloadOnSourceChanges
