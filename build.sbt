name := "akka-http-example"
organization := "com.example"
version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {

  val akkaV = "2.4.5"
  val scalaTestV = "2.2.6"

  Seq(
    "com.typesafe.akka"       %% "akka-actor"                         % akkaV,
    "com.typesafe.akka"       %% "akka-stream"                        % akkaV,
    "com.typesafe.akka"       %% "akka-http-core"                     % akkaV,
    "com.typesafe.akka"       %% "akka-http-experimental"             % akkaV,
    "com.typesafe.akka"       %% "akka-http-spray-json-experimental"  % akkaV,
    "com.typesafe.akka"       %% "akka-http-testkit"                  % akkaV,
    "com.typesafe.akka"       %% "akka-slf4j"                         % akkaV,
    "com.typesafe.akka"       %% "akka-cluster"                       % akkaV,
    "com.typesafe.akka"       %% "akka-contrib"                       % akkaV,
    "org.scalatest"           %% "scalatest"                          % scalaTestV,
    "ch.qos.logback"          % "logback-classic"                     % "1.1.3",
    "ch.qos.logback"          % "logback-core"                        % "1.1.3",
    "net.logstash.logback"    % "logstash-logback-encoder"            % "4.3",
    "org.slf4j"               % "slf4j-api"                           % "1.7.16"
  )
}


// Resolving issue:
// [warn] Multiple dependencies with the same organization/name but different versions. To avoid conflict, pick one version:
//libraryDependencies ++= Seq(
//  "org.scala-lang"          % "scala-reflect"   % "2.11.8",
//  "org.scala-lang.modules"  % "scala-xml_2.11"  % "1.0.4"
//)