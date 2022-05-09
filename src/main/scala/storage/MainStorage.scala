package storage

import utils.Report

import storage.Storage._

import org.apache.spark.sql.{SparkSession, Encoders, DataFrame}
import org.apache.spark.sql.functions.from_json
import org.apache.spark.sql.functions._

object MainStorage extends App {

  override def main (args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Spark-Kafka-App")
      .master("local")
      .getOrCreate()
    
    // “value $ is not a member of StringContext”
    // https://stackoverflow.com/questions/44209756/value-is-not-a-member-of-stringcontext-missing-scala-plugin
    import spark.implicits._

    val mySchema = Encoders.product[Report].schema

    val kafkaDF: DataFrame = spark      
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "reports")
      .option("startingOffsets", "earliest")
      .load()

    val arrayString = udf((values: Seq[String]) => values match {
      case null => null
      case _ => s"""[${values.mkString(",")}]"""
    })

    val kafka = kafkaDF
      .select($"value".cast("string").alias("value"))
      .select(from_json($"value", mySchema).as("report"))
      .selectExpr(
        "report.peacewatcherID", 
        "report.peacewatcherLat", 
        "report.peacewatcherLon",
        "report.CitizensID",
        "report.CitizensPeaceScore",
        "report.Words",
        "report.datetimeReport"
      )

      .withColumn("CitizensID", arrayString($"CitizensID"))
      .withColumn("CitizensPeaceScore", arrayString($"CitizensPeaceScore"))
      .withColumn("Words", arrayString($"Words"))

    // Here we need to chose between local (1) and HDFS (2) storage
    storage(kafka, "30 seconds", "hdfs://localhost:9000/data/") // "ressources/reports/"
  }

}