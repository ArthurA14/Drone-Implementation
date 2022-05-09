package utils

import com.google.gson.{Gson, JsonObject}

// THIS IS A CASE CLASS MODEL FOR A CITIZEN
case class Citizen(id : Int, name : String, score : Double)

object Citizen {

  // get data from the json file (coming from https://www.mockaroo.com/)
	def parseFromJson(line: String): Citizen = {
		val gson = new Gson
    val jsonStringInput = line
    val citizen: Citizen = gson.fromJson(jsonStringInput, classOf[Citizen])
    citizen
	}

}
