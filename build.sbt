name := "droneScalaProject"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.github.stevenchen3" %% "scala-faker" % "0.1.1",
  "org.apache.hadoop" % "hadoop-hdfs" % "2.4.0",
  "com.lihaoyi" %% "upickle" % "0.7.1",
  "com.lihaoyi" %% "os-lib" % "0.2.7",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5",
  "com.typesafe.play" %% "play-json" % "2.6.7",
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" %% "spark-streaming" % "2.4.0",
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.0",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.0",
  "org.apache.kafka" %% "kafka" % "2.4.0",
  "org.apache.kafka" % "kafka-clients" % "2.4.0",
  "org.apache.kafka" % "kafka-streams" % "2.4.0"
)
