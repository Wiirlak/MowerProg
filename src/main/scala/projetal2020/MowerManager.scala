package projetal2020
import scala.annotation.tailrec

case class MowerManager(
    mowers: List[Mower],
    land: Land
) {

  def run_all_mowers(): List[MowerEnded] = {
    @tailrec
    def run_one_mower(
        mowers_list: List[Mower],
        mowerEndedAcc: List[MowerEnded]
    ): List[MowerEnded] = mowers_list match {
      case Nil => mowerEndedAcc
      case head :: tail =>
        val mowerEnded = MowerEnded(
          head.land,
          head.sequence,
          head.start_coordinates,
          head.run_sequence()
        )
        run_one_mower(tail, mowerEndedAcc :+ mowerEnded)
    }

    run_one_mower(mowers, List())
  }
}
