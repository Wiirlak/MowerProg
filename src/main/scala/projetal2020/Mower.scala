package projetal2020
import scala.annotation.tailrec

class Mower(
    val start_x: Int,
    val start_y: Int,
    val start_direction: Char,
    val sequence: String
) {

  println("New mower")
  print_current()

//  make_it_move(start_direction, sequence(0))
  println(move_it( /*start_x, start_y, start_direction,*/ sequence))

  def move_it(str: String): (Int, Int, Char) = {
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
    move(str.toList, Nil, start_x, start_y, start_direction)

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
        case 'A' => (current_x, current_y + 1, direction)
        case 'D' => (current_x, current_y, 'E')
//        case _   => println("error")
      }

    case 'W' =>
      movement match {
        case 'G' => (current_x, current_y, 'S')
        case 'A' => (current_x - 1, current_y, direction)
        case 'D' => (current_x, current_y, 'N')
//        case _   => println("error")
      }

    case 'E' =>
      movement match {
        case 'G' => (current_x, current_y, 'N')
        case 'A' => (current_x + 1, current_y, direction)
        case 'D' => (current_x, current_y, 'S')
//        case _   => println("error")
      }

    case 'S' =>
      movement match {
        case 'G' => (current_x, current_y, 'E')
        case 'A' => (current_x, current_y - 1, direction)
        case 'D' => (current_x, current_y, 'W')
//        case _   => println("error")
      }

//    case _ => println("error")
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
