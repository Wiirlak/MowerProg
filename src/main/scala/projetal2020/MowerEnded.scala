package projetal2020

case class MowerEnded(
    override val land: Land,
    override val sequence: String,
    override val start_coordinates: Coordinates,
    coordinates: Coordinates
) extends Mower(start_coordinates, sequence, land) {}
