package consumer

import utils._

import consumer.Consumer._

import java.util.{Properties, Timer}

import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

object MainConsumer extends App {

  override def main(args: Array[String]): Unit = {  // method main needs `override' modifier

    // KAFKA CONSUMER PROPERTIES
    val properties: Properties = new Properties()
    properties.put("auto.offset.reset", "latest")
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumergroup")   // you must provide a valid group.id in the consumer configuration scala error

    // CREATE A CONSUMER INSTANCE
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](properties)

    readFromKafka(consumer, "reports")
  }

}