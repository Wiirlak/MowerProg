package projetal2020

import scala.annotation.tailrec

object Parser {

  def get_land_from_list(land_list: List[String]): Land = {
    val land_data = land_list(0).split(" ")
    val size_x = land_data(0).toInt
    val size_y = land_data(1).toInt
    new Land(size_x, size_y)
  }

  def get_mowers_from_list(
      mower_list: List[String],
      land: Land
  ): List[Mower] = {

    // TODO gerer les erreurs d'input
    @tailrec
    def append(
        mower_list: List[String],
        land: Land,
        mowers_acc: List[Mower]
    ): List[Mower] = mower_list match {
      case Nil => mowers_acc
      case head :: tail => {
        val r = head.split(" ")
        val x = r(0).toInt
        val y = r(1).toInt
        val dir = r(2).charAt(0)
        append(
          tail.drop(1),
          land,
          mowers_acc :+ new Mower(Coordinates(x, y, dir), tail(0), land)
        )
      }
    }

    append(mower_list.drop(1), land, List())
  }
}
