package projetal2020
import play.api.libs.json._
import scala.annotation.tailrec

class MowerManager(
    val mowers: List[Mower],
    val land: Land
) {
  show_output()

  def run_mower(): String = {
    @tailrec
    def run_one(rest: List[Mower], res: String): String = {
      rest match {
        case Nil => res
        case head :: tail => {
          val end = head.move_it()
          val json: JsValue = Json.parse(
            s"""
               |\t\t\t{
               |\t\t\t\t"debut": {
               |\t\t\t\t\t"point": {
               |\t\t\t\t\t\t"x": ${head.start_x.toString},
               |\t\t\t\t\t\t"y": ${head.start_y.toString}
               |\t\t\t\t\t},
               |\t\t\t\t\t"direction": "${head.start_direction.toString}"
               |\t\t\t\t},
               |\t\t\t\t"instructions": ["${head.sequence
                 .split("")
                 .mkString("\", \"")}"],
               |\t\t\t\t"fin": {
               |\t\t\t\t\t"point": {
               |\t\t\t\t\t\t"x": ${end._1.toString},
               |\t\t\t\t\t\t"y": ${end._2.toString}
               |\t\t\t\t\t},
               |\t\t\t\t\t"direction": "${end._3.toString}"
               |\t\t\t\t}
               |\t\t\t}
               |""".stripMargin
          )
          run_one(tail, List(res, json.toString()).mkString(","))
        }
      }
    }
    run_one(mowers, "")
  }

  def show_output(): Unit = {
    val json: JsValue = Json.parse(s"""
         |{
         |\t"limite": {
         |\t\t"x": ${land.size_x.toString},
         |\t\t"y": ${land.size_y.toString}
         |\t},
         |\t"tondeuses": [${run_mower().substring(1)}]
         |}
         |""".stripMargin)
    println(Json.prettyPrint(json))
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
//val json: JsValue = Json.parse(
//s"""
//               |\t\t\t{
//               |\t\t\t\t"debut": {
//               |\t\t\t\t\t"point": {
//               |\t\t\t\t\t\t"x": ${head.start_x.toString},
//               |\t\t\t\t\t\t"y": ${head.start_y.toString}
//               |\t\t\t\t\t},
//               |\t\t\t\t\t"direction": "${head.start_direction.toString}"
//               |\t\t\t\t},
//               |\t\t\t\t"instructions": ["${head.sequence
//.split("")
//.mkString("\", \"")}"],
//               |\t\t\t\t"fin": {
//               |\t\t\t\t\t"point": {
//               |\t\t\t\t\t\t"x": ${end._1.toString},
//               |\t\t\t\t\t\t"y": ${end._2.toString}
//               |\t\t\t\t\t},
//               |\t\t\t\t\t"direction": "${end._3.toString}"
//               |\t\t\t\t}
//               |\t\t\t}
//               |""".stripMargin
//)
