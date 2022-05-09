package analysis
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Column, DataFrame, Encoders, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.regexp_replace


object Analysis {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)
    val pathToFile = "hdfs://localhost:9000/data/reportsFiles/*.csv"
    //val pathToFile = "ressources/reports/"

    val spark = SparkSession
      .builder
      .appName("Peacemaker-Analysis")
      .master("local")
      .getOrCreate()

    val raw_df = spark.read
      .options(Map("inferSchema" -> "true", "delimiter" -> ";"))
      .option("header", false)
      .csv(pathToFile)
      .toDF(
      "peacewatcherID",
      "Peacewatcherlat",
      "Peacewatcherlon",
      "CitizensID",
      "CitizensPeaceScore",
      "Words",
      "datetimeReport"
      )

    val clean_df = raw_df
      .withColumn("Words",regexp_replace(raw_df("Words"), "[\\[ \\]]", ""))
      .withColumn("CitizensID",regexp_replace(raw_df("CitizensID"), "[\\[\\]]", ""))
      .withColumn("CitizensPeaceScore",regexp_replace(raw_df("CitizensPeaceScore"), "[\\[\\]]", ""))
      .withColumn("datetimeReport", to_timestamp(col("datetimeReport"), "dd-MM-yyyy HH:mm"))
      .select(
        col("peacewatcherID"),
        col("peacewatcherLat"),
        col("peacewatcherLon"),
        split(col("CitizensID"),",").as("CitizensID"),
        split(col("CitizensPeaceScore"),",").as("CitizensPeaceScore"),
        split(col("Words"),",").as("Words"),
        col("datetimeReport")
      )

    println("")
    println("##################### Peaceland KPIs #####################")
    println("")

    println("Cleaned Dataframe Preview:")
    clean_df.orderBy(col("CitizensPeaceScore").desc).show(10)
    println("")
    

    // Days of the month with the most citizens recorded
    def citizensPerDay(df : DataFrame): DataFrame = {
      df.withColumn("Days",dayofmonth(col("datetimeReport")))
        .select(col("Days"),size(col("CitizensID")).as("CitizensCount"))
        .groupBy("Days").sum().drop("sum(Days)")
        .orderBy(col("sum(CitizensCount)").desc)
    }
    println("Count of citizens per day of the month:")
    citizensPerDay(clean_df).show(10)

    // Peacelanders that are the most recorded : pro trouble makers
    def MostObservedCitizens(df : DataFrame): DataFrame = {
      df.select(
        explode(col("CitizensID")).as("CitizenID"))
        .groupBy("CitizenID")
        .count()
        .orderBy(col("count").desc)
    }
    println("Most recorded Peacelanders:")
    MostObservedCitizens(clean_df).show(10)

    // top words used by Peacelanders
    def TopWords(df : DataFrame): DataFrame = {
      df.select(
        explode(col("Words")).as("Word"))
        .groupBy("Word")
        .count()
        .orderBy(col("count").desc)
    }
    println("Top words used among Peacelanders:")
    TopWords(clean_df).show(10)

    // top most common PeaceScores
    def MostCommonPeaceScores(df : DataFrame): DataFrame = {
      df.select(
        explode(col("CitizensPeaceScore")).as("CitizenPeaceScore"))
        .groupBy("CitizenPeaceScore")
        .count()
        .orderBy(col("count").desc)
    }
    println("Top 10 most common PeaceScores:")
    MostCommonPeaceScores(clean_df).show(10)

    // top worst Peacelanders
    def WorstPeople(df: DataFrame, limit: Double): DataFrame = {
      df.withColumn("WorstScore", array_min(col("CitizensPeaceScore")))
        .filter(col("WorstScore") < limit)
        .withColumn("CitizenID", explode(col("CitizensID")))
        .groupBy("CitizenID")
        .count()
        .sort(col("count").desc)
    }
    println("Top 10 worst Recidivists:")
    WorstPeople(clean_df, limit=10.0).show(10)

  }
}

