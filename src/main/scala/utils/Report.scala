package utils

// THIS IS A CASE CLASS MODEL FOR A DRONE REPORT
case class Report(peacewatcherID : Int,
                  peacewatcherLat: Double,
                  peacewatcherLon: Double,
                  CitizensID : Array[Int],
                  CitizensPeaceScore : Array[Double],
                  Words : Array[String],
                  datetimeReport : String) {
  
   override def toString: String = s"PeacewatcherID : ${peacewatcherID} ; " +
     s"PeacewatcherLat: ${peacewatcherLat} ; " +
     s"PeacewatcherLon: ${peacewatcherLon} " +
     s"; CitizensID: ${CitizensID.mkString(",")} ; " +
     s"CitizensPeaceScore: ${CitizensPeaceScore.mkString(",")} ; " +
     s"Words: ${Words.mkString(",")} ; " +
     s"DateTime : ${datetimeReport}"

}