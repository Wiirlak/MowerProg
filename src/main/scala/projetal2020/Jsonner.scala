package projetal2020

import play.api.libs.json.{JsArray, JsObject, Json}

import scala.annotation.tailrec

object Jsonner {
  def landToJson(land: Land): JsObject = {
    Json.obj("x" -> land.size_x, "y" -> land.size_y)
  }

  def mowersListToJson(mowerList: List[MowerEnded]): JsArray = {
    @tailrec
    def mowerToJson(mowerList: List[MowerEnded], json_arr: JsArray): JsArray =
      mowerList match {
        case Nil => json_arr
        case head :: tail =>
          mowerToJson(tail, json_arr :+ mowerEndedToJson(head))
      }
    mowerToJson(mowerList, Json.arr())
  }

  def mowerEndedToJson(mower: MowerEnded): JsObject = {
    Json.obj(
      "debut" -> Json.obj(
        "point" -> Json.obj(
          "x" -> mower.start_coordinates.x,
          "y" -> mower.start_coordinates.y
        ),
        "direction" -> mower.start_coordinates.direction.toString
      ),
      "instructions" -> mower.sequence.split(""),
      "fin" -> Json.obj(
        "point" -> Json
          .obj("x" -> mower.coordinates.x, "y" -> mower.coordinates.y),
        "direction" -> mower.coordinates.direction.toString
      )
    )
  }

  def runToJson(land: Land, mowersList: List[MowerEnded]): String = {
    Json.prettyPrint(
      Json.obj(
        "limite"    -> landToJson(land),
        "tondeuses" -> mowersListToJson(mowersList)
      )
    )
  }
}
