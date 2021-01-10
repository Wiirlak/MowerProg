package projetal2020
import play.api.libs.json._

import scala.collection.mutable.ListBuffer

//@SuppressWarnings(Array("org.wartremover.warts.NonUnitStatements")) // todo: remove that shit
class MowerManager(
    val mowers: ListBuffer[Mower]
) {

  val end_mowers: ListBuffer[(Int, Int, Char)] = run()
  show_output()

  def run(): ListBuffer[(Int, Int, Char)] = {
    mowers.map(x => x.move_it())
  }

  def show_output(): Unit = {
    val susu: Int = 5
    val json: JsValue = Json.parse(s"""
         |{
         |\t"limite": {
         |\t\t"x": ${susu.toString},
         |\t\t"y": 5
         |\t},
         |\t"tondeuses": [${mowerToString()}]
         |}
         |""".stripMargin)
    println(json)
  }

  def mowerToString(): String = {
    val res = mowers.map(
      x =>
        s"""
        |\t\t\t{
        |\t\t\t\t"debut": {
        |\t\t\t\t\t"point": {
        |\t\t\t\t\t\t"x": ${x.start_x.toString},
        |\t\t\t\t\t\t"y": ${x.start_y.toString}
        |\t\t\t\t\t},
        |\t\t\t\t\t"direction": "${x.start_direction.toString}"
        |\t\t\t\t},
        |\t\t\t\t"instructions": ["${x.sequence
             .split("")
             .mkString("\", \"")}"],
        |\t\t\t\t"fin": {
        |\t\t\t\t\t"point": {
        |\t\t\t\t\t\t"x": ${x.start_x.toString},
        |\t\t\t\t\t\t"y": ${x.start_y.toString}
        |\t\t\t\t\t},
        |\t\t\t\t\t"direction": "${x.start_direction.toString}"
        |\t\t\t\t}
        |\t\t\t}
        |""".stripMargin
    )
    res.mkString(",")
  }

}

/* todo : clean at the end
 Old one (plus propre)
//    val json: JsValue = JsObject(
//      Seq(
//        "limite" -> JsObject(
//          Seq(
//            "x" -> JsNumber(5),
//            "y" -> JsNumber(5)
//          )
//        ),
//        "tondeuses" -> JsArray(
//          IndexedSeq(
//            JsObject(
//              Seq(
//                "debut" -> JsObject(
//                  Seq(
//                    "point" -> JsObject(
//                      Seq(
//                        "x" -> JsNumber(5),
//                        "y" -> JsNumber(5)
//                      )
//                    ),
//                    "direction" -> JsString("N")
//                  )
//                ),
//                "instructions" -> JsArray(
//                  IndexedSeq(
//                    JsString("G"),
//                    JsString("D"),
//                    JsString("A")
//                  )
//                ),
//                "fin" -> JsObject(
//                  Seq(
//                    "point" -> JsObject(
//                      Seq(
//                        "x" -> JsNumber(5),
//                        "y" -> JsNumber(5)
//                      )
//                    ),
//                    "direction" -> JsString("N")
//                  )
//                )
//              )
//            )
//          )
//        )
//      )
//    )
 */
