package producer

import utils._

import java.util.Properties
import org.apache.kafka.clients.producer.{ProducerRecord, KafkaProducer}

import scala.util.Random
import play.api.libs.json._
import play.api.libs.json.Json

object Producer {

  // Read entire file in Scala
  // https://stackoverflow.com/questions/1284423/read-entire-file-in-scala
  def wordsGenerator(): List[String] = {
    val textFile = scala.io.Source.fromFile("ressources/keywords.txt")
    val words = textFile.getLines.toList
    textFile.close()
    words
  }

  def droneGenerator(words: List[String]): Array[Drone] = {

    val workingDirectory = os.pwd / "ressources" / "residents.json"
    val citizens = os.read.lines(workingDirectory)
                          .map(Citizen.parseFromJson)
                          .toArray
    // number of drones
    val identifiers = List.range(0, 101)
    val drones = List.range(0, 101)
                     .map(drone => Drone(identifiers(drone), citizens, words))
                     .toArray
    drones
  }

  def writeInKafka(producer: KafkaProducer[String, String], topic: String, report: Report): Unit = {
    implicit val reportWrites: OWrites[Report] = Json.writes[Report]
    println(report.toString)
    val jsonReport = Json.toJson(report)
    val record: ProducerRecord[String, String] = 
      new ProducerRecord[String, String](topic, report.peacewatcherID.toString, jsonReport.toString)
    producer.send(record)
    // producer.close() java.lang.IllegalStateException: Cannot perform operation after producer has been closed
  }

  def getRandomFromList[T](n: Int, list: List[T]) = Random.shuffle(list).take(n)

}
