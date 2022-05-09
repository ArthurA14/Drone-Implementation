package utils

import scala.util.Random

// THIS IS A CASE CLASS MODEL FOR AN ALERT MESSAGE
case class Alert (report : Report) {
  
  def findBadPeacelander(): Unit = {
    
    val report = this.report
    val latitude = report.peacewatcherLat
    val longitude = report.peacewatcherLon
    // val rnd = new scala.util.Random
    val workingDirectory = os.pwd / "ressources" / "residents.json"
    val Citizens = os.read.lines(workingDirectory).map(Citizen.parseFromJson).toArray
    val citizensID = report.CitizensID
    val citizensPeaceScore = report.CitizensPeaceScore

    (citizensID, citizensPeaceScore).zipped.foreach { (citizenID, citizensPeaceScore) =>
      val citizen = Citizens.filter(_.id == citizenID).head
      val citizenId = citizen.id
      val citizenName = citizen.name
      val peaceScore = citizensPeaceScore
      if (peaceScore < 10) {
        println("WARNING ! We have a problem with a bad peacelander. \n" +
          "Location : " + "(" + latitude + ", " + longitude + "). \n" +
          "This citizen : (id : " + citizenId + ", name: " + citizenName + ") has a bad peacescore of " + peaceScore + ", \n" +
          "so has to go to a re-education camp, breaking rocks by hand.\n")
      }
    }
  }

}