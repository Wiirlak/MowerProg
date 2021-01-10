package projetal2020

import scala.collection.mutable.ListBuffer

class Parser {

  def get_land_from_list(land_list: List[String]): Land = {
    val land_data = land_list(0).split(" ")
    val size_x = land_data(0).toInt
    val size_y = land_data(1).toInt
    new Land(size_x, size_y)
  }

  def get_mowers_from_list(
      mower_list: List[String],
      land: Land
  ): ListBuffer[Mower] = {
    val mowers = new ListBuffer[Mower]()
    for (i <- 1 until mower_list.size by 2) {
      val r = mower_list(i).split(" ")
      val x = r(0).toInt
      val y = r(1).toInt
      val dir = r(2).charAt(0)
      mowers += new Mower(x, y, dir, mower_list(i + 1), land)
    }
    mowers
  }
}
