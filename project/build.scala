import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.Keys._
import sbt.Keys._

object Settings {
  lazy val common = Seq(
    organization := "com.example",
    version := "1.0",
    scalaVersion := "2.11.7",
    scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"))
}

object ProductApi {
  val settings = Seq(
    dockerBaseImage := "robsonoduarte/8-jre-alpine-bash",
    dockerExposedPorts := Seq(8000),
    dockerRepository := Some("distributed-systems-course"),
    packageName in Docker := name.value,
    dockerUpdateLatest := true,
//    version in Docker := "prod",
    defaultLinuxInstallLocation in Docker := s"/opt/${name.value}")
}

object UserApi {
  val settings = Seq(
    dockerBaseImage := "robsonoduarte/8-jre-alpine-bash",
    dockerExposedPorts := Seq(8000),
    dockerRepository := Some("distributed-systems-course"),
    packageName in Docker := name.value,
    dockerUpdateLatest := true,
//    version in Docker := "prod",
    defaultLinuxInstallLocation in Docker := s"/opt/${name.value}")
}
