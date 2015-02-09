import com.lihaoyi.workbench.Plugin._

enablePlugins(ScalaJSPlugin)

workbenchSettings

name := "Convergence"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.0",
  "com.lihaoyi" %%% "scalatags" % "0.4.5"
)

bootSnippet := "convergence.ConditionalConvergence().main(document.getElementById('cc'));"

updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)
