package storage

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.Trigger

object Storage {

  def storage(kf: DataFrame, interval: String, path: String): Unit ={
    kf.writeStream
      .trigger(Trigger.ProcessingTime(interval))
      .format("csv")
      .option("checkpointLocation", path + "checkpoint/")
      .option("path", path + "reportsFiles/")
      .option("delimiter", ";")
      .option("header", false)
      .start()
      .awaitTermination()
  }

}




