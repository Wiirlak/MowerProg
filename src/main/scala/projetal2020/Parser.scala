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
      case head :: tail =>
        val row = head.split(" ")
        val x = row(0).toInt
        val y = row(1).toInt
        val dir = row(2).charAt(0)

        check_values(Coordinates(x, y, dir), tail(0))

        append(
          tail.drop(1),
          land,
          mowers_acc :+ new Mower(Coordinates(x, y, dir), tail(0), land)
        )
    }

    append(mower_list.drop(1), land, List())
  }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def check_values(coordinates: Coordinates, sequence: String): Unit = {
    if (!sequence.matches("^[A,D,G]*$")) {
      throw DonneesIncorrectesException(
        "Error on sequence. It should be within [G, D, A]"
      )
    }
    if (!"NEWS".contains(coordinates.direction)) {
      throw DonneesIncorrectesException(
        "Error on start position. It should be within [N, E, W, S]"
      )
    }
    if (coordinates.x < 0 || coordinates.y < 0) {
      throw DonneesIncorrectesException(
        "Error on start position. Mower should start x and y > 0"
      )
    }
  }
}
