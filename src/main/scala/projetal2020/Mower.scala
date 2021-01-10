package projetal2020
import scala.annotation.tailrec

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
class Mower(
    val start_x: Int,
    val start_y: Int,
    val start_direction: Char,
    val sequence: String,
    val land: Land
) {

  if (!sequence.matches("^[A,D,G]*$")) {
    throw DonneesIncorrectesException(
      "Error on sequence. Should be in [G, D, A]"
    )
  }

  if (!"NEWS".contains(start_direction)) {
    throw DonneesIncorrectesException(
      "Error on start position. Should be [N, E, W, S]"
    )
  }

  //TODO: starting pos x et y < 0

  println("New mower")
  print_current()

//  println(move_it(sequence))

  def move_it(): (Int, Int, Char) = {
    @tailrec
    def move(
        element: List[Char],
        result: List[Char],
        current_x: Int,
        current_y: Int,
        direction: Char
    ): (Int, Int, Char) =
      element match {
        case Nil => (current_x, current_y, direction)
        case head :: tail =>
          val (l, m, n) = make_it_move(current_x, current_y, direction, head)
          move(tail, head :: result, l, m, n)
      }
    move(sequence.toList, Nil, start_x, start_y, start_direction)
  }

  def make_it_move(
      current_x: Int,
      current_y: Int,
      direction: Char,
      movement: Char
  ): (Int, Int, Char) = direction match {
    case 'N' =>
      movement match {
        case 'G' => (current_x, current_y, 'W')
        case 'D' => (current_x, current_y, 'E')
        case 'A' =>
          if (current_y < this.land.size_y)
            (current_x, current_y + 1, direction)
          else (current_x, current_y, direction) //TODO: error here
      }

    case 'W' =>
      movement match {
        case 'G' => (current_x, current_y, 'S')
        case 'D' => (current_x, current_y, 'N')
        case 'A' =>
          if (current_y > 0) (current_x - 1, current_y, direction)
          else (current_x, current_y, direction) //TODO: error here
      }

    case 'E' =>
      movement match {
        case 'G' => (current_x, current_y, 'N')
        case 'D' => (current_x, current_y, 'S')
        case 'A' =>
          if (current_x < this.land.size_x)
            (current_x + 1, current_y, direction)
          else (current_x, current_y, direction) //TODO: error here
      }

    case 'S' =>
      movement match {
        case 'G' => (current_x, current_y, 'E')
        case 'D' => (current_x, current_y, 'W')
        case 'A' =>
          if (current_y > 0) (current_x, current_y - 1, direction)
          else (current_x, current_y, direction) //TODO: error here
      }
  }

  def print_current(): Unit = {
    println(this.start_x)
    println(this.start_y)
    println(this.start_direction)
    println(this.sequence)
  }

  def write_in_file(x: Int, y: Int, d: Char): Unit = {
    println(x)
    println(y)
    println(d)
  }
}
