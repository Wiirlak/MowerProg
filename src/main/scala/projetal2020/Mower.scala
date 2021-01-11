package projetal2020
import scala.annotation.tailrec

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
class Mower(
    val start_coordinates: Coordinates,
    val sequence: String,
    val land: Land
) {

  if (!sequence.matches("^[A,D,G]*$")) {
    throw DonneesIncorrectesException(
      "Error on sequence. Should be in [G, D, A]"
    )
  }

  if (!"NEWS".contains(start_coordinates.direction)) {
    throw DonneesIncorrectesException(
      "Error on start position. Should be [N, E, W, S]"
    )
  }

  //TODO: starting pos x et y < 0

  def move_it(): Coordinates = {
    @tailrec
    def move(
        element: List[Char],
        result: List[Char],
        coordinates: Coordinates
    ): Coordinates =
      element match {
        case Nil => coordinates
        case head :: tail =>
          val new_coordinates: Coordinates = make_it_move(
            coordinates.x,
            coordinates.y,
            coordinates.direction,
            head
          )
          move(tail, head :: result, new_coordinates)
      }
    move(sequence.toList, Nil, start_coordinates)
  }

  def make_it_move(
      current_x: Int,
      current_y: Int,
      direction: Char,
      movement: Char
  ): Coordinates = direction match {
    case 'N' =>
      movement match {
        case 'G' => Coordinates(current_x, current_y, 'W')
        case 'D' => Coordinates(current_x, current_y, 'E')
        case 'A' =>
          if (current_y < this.land.size_y)
            Coordinates(current_x, current_y + 1, direction)
          else Coordinates(current_x, current_y, direction) //TODO: error here
      }

    case 'W' =>
      movement match {
        case 'G' => Coordinates(current_x, current_y, 'S')
        case 'D' => Coordinates(current_x, current_y, 'N')
        case 'A' =>
          if (current_y > 0) Coordinates(current_x - 1, current_y, direction)
          else Coordinates(current_x, current_y, direction) //TODO: error here
      }

    case 'E' =>
      movement match {
        case 'G' => Coordinates(current_x, current_y, 'N')
        case 'D' => Coordinates(current_x, current_y, 'S')
        case 'A' =>
          if (current_x < this.land.size_x)
            Coordinates(current_x + 1, current_y, direction)
          else Coordinates(current_x, current_y, direction) //TODO: error here
      }

    case 'S' =>
      movement match {
        case 'G' => Coordinates(current_x, current_y, 'E')
        case 'D' => Coordinates(current_x, current_y, 'W')
        case 'A' =>
          if (current_y > 0) Coordinates(current_x, current_y - 1, direction)
          else Coordinates(current_x, current_y, direction) //TODO: error here
      }
  }
}
