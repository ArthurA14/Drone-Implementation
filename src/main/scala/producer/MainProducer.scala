package producer

import utils._

import producer.Producer._

import Time.timeFunc
import java.util.{Properties, Timer}

import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

object MainProducer extends App {

  override def main(args: Array[String]): Unit = {

    val drones = droneGenerator(wordsGenerator())

    // KAFKA PRODUCER PROPERTIES
    val properties: Properties = new Properties()
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])

    // CREATE A PRODUCER INSTANCE
    val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](properties)
    val topic = "reports"

    //TIMER
    def timeTask() = drones.foreach(drone => writeInKafka(producer, topic, drone.generateReport()))

    val timeProducer = new Timer()
    timeProducer.schedule(timeFunc(timeTask), 1000, 60000)
    Thread.sleep(1000)

  }

}
