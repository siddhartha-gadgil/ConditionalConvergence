import com.lihaoyi.workbench.Plugin._

enablePlugins(ScalaJSPlugin)

name := "Convergence"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.0",
  "com.lihaoyi" %%% "scalatags" % "0.5.2"
)
