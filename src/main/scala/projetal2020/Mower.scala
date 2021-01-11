package projetal2020
import scala.annotation.tailrec

class Mower(
    val start_coordinates: Coordinates,
    val sequence: String,
    val land: Land
) {

  def run_sequence(): Coordinates = {
    @tailrec
    def action(
        element: List[Char],
        result: List[Char],
        coordinates: Coordinates
    ): Coordinates =
      element match {
        case Nil => coordinates
        case head :: tail =>
          val new_coordinates: Coordinates = move(
            coordinates,
            head
          )
          action(tail, head :: result, new_coordinates)
      }
    action(sequence.toList, Nil, start_coordinates)
  }

  def move(
      coordinates: Coordinates,
      movement: Char
  ): Coordinates = coordinates.direction match {
    case 'N' =>
      movement match {
        case 'G' => Coordinates(coordinates.x, coordinates.y, 'W')
        case 'D' => Coordinates(coordinates.x, coordinates.y, 'E')
        case 'A' =>
          if (coordinates.y < this.land.size_y)
            Coordinates(coordinates.x, coordinates.y + 1, coordinates.direction)
          else Coordinates(coordinates.x, coordinates.y, coordinates.direction)
      }

    case 'W' =>
      movement match {
        case 'G' => Coordinates(coordinates.x, coordinates.y, 'S')
        case 'D' => Coordinates(coordinates.x, coordinates.y, 'N')
        case 'A' =>
          if (coordinates.x > 0)
            Coordinates(coordinates.x - 1, coordinates.y, coordinates.direction)
          else Coordinates(coordinates.x, coordinates.y, coordinates.direction)
      }

    case 'E' =>
      movement match {
        case 'G' => Coordinates(coordinates.x, coordinates.y, 'N')
        case 'D' => Coordinates(coordinates.x, coordinates.y, 'S')
        case 'A' =>
          if (coordinates.x < this.land.size_x)
            Coordinates(coordinates.x + 1, coordinates.y, coordinates.direction)
          else Coordinates(coordinates.x, coordinates.y, coordinates.direction)
      }

    case 'S' =>
      movement match {
        case 'G' => Coordinates(coordinates.x, coordinates.y, 'E')
        case 'D' => Coordinates(coordinates.x, coordinates.y, 'W')
        case 'A' =>
          if (coordinates.y > 0)
            Coordinates(coordinates.x, coordinates.y - 1, coordinates.direction)
          else Coordinates(coordinates.x, coordinates.y, coordinates.direction)
      }
  }
}
