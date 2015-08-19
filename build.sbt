name := "Scala-Camel-JMS"

version := "1.0"

//scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.apache.activemq" % "activemq-camel" % "5.10.1",
  "org.hornetq" % "hornetq-jms" % "2.2.6.Final",
  "org.hornetq" % "hornetq-core" % "2.2.6.Final",
  "org.apache.camel" % "camel-core" % "2.11.4",
  "org.apache.camel" % "camel-amqp" % "2.11.4",
  "org.apache.camel" % "camel-scala" % "2.11.4",
  "org.apache.camel" % "camel-jms" % "2.11.4",
  "log4j" % "log4j" % "1.2.16",
  "commons-logging" % "commons-logging" % "1.2",
  "jboss" % "jbossall-client" % "3.2.6", // check
  "org.springframework.boot" % "spring-boot-starter-web" % "1.2.3.RELEASE",
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % "1.2.3.RELEASE",
  "org.springframework.boot" % "spring-boot-starter-web" % "1.2.3.RELEASE",
  "io.netty" % "netty" % "3.9.2.Final",
  "com.typesafe.akka" %% "akka-actor" % "2.1.2",
  "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test",
  "io.spray" %% "spray-json" % "1.3.1",
  "org.scala-lang" % "scala-reflect" % "2.10.2",
  "io.spray" %% "spray-json" % "1.3.2",
  "org.springframework.data" % "spring-data-mongodb" % "1.7.0.RELEASE"
)

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.0-alpha4"

mainClass in Compile := Some("camel.CamelStarter")

assemblyJarName in assembly := s"${name.value}-${version.value}"
