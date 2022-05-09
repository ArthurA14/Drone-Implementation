package consumer

import utils.{Report, Alert}

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import java.util
import java.time.Duration
import java.util.{Properties, Timer}

import utils.Time.timeFunc

import scala.collection.JavaConversions.asScalaIterator

object Consumer {

  def createAlert(reportStringInput : String): Unit = {
    val dataCol = ujson.read(reportStringInput)
    val report: Report = Report(
      dataCol("peacewatcherID").toString().toInt,
        dataCol("peacewatcherLat").toString().toDouble,
          dataCol("peacewatcherLon").toString().toDouble,
            dataCol("CitizensID").toString().replaceAll("[\\[\\]]", "").split(",").map(_.toInt),
              dataCol("CitizensPeaceScore").toString().replaceAll("[\\[\\]]", "").split(",").map(_.toDouble),
                dataCol("Words").toString().replaceAll("[\\[\\]]", "").split(","),
                  dataCol("datetimeReport").toString()
    )
    val alert = Alert(report)
    alert.findBadPeacelander()
  }

  // https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
  def readFromKafka(consumer: KafkaConsumer[String, String], topic: String): Unit = {
    consumer.subscribe(util.Arrays.asList(topic))
    def timeTask() = {
      val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(1000))
      records.iterator.foreach(record => createAlert(record.value()))
    }

    val timerConsumer = new Timer()
    timerConsumer.schedule(timeFunc(timeTask), 1000, 1000)
    Thread.sleep(50000)

  }

}


