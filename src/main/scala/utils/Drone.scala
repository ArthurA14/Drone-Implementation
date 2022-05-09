package utils

import java.text.SimpleDateFormat
import java.util.Calendar

import io.alphash.faker.Geolocation

import scala.util.Random

// THIS IS A CASE CLASS MODEL FOR A DRONE 
case class Drone (id : Int, citizens : Array[Citizen], words : List[String]) {

  // get n random element from a list
  def getRandomFromList[T](n: Int, list: List[T]) = Random.shuffle(list).take(n)

  def generateReport(): Report = {
    val format = new SimpleDateFormat("d-M-y HH:mm")
    val rnd = new scala.util.Random
    // at least 2 citizens (max 25) together and a conversation of 20 words (max 150)
    // https://stackoverflow.com/questions/16332938/java-random-number-but-not-zero
    val n = rnd.nextInt(24) + 2
    val w = rnd.nextInt(131) + 20
    Report (
        this.id,
        new Geolocation().latitute, // get fake data with faker lib
        new Geolocation().longitude,  // get fake data with faker lib
        getRandomFromList(n, citizens.map(_.id).toList).toArray,  // List.fill(n)(citizens.map(_.id)).toMap,
        getRandomFromList(n, citizens.map(_.score).toList).toArray,
        getRandomFromList(w, words).toArray, // generate words : Lorem.words(w),
        format.format(Calendar.getInstance().getTime))
  }
}