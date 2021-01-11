package projetal2020

import org.scalatest.funsuite.AnyFunSuite
import projetal2020.{DonneesIncorrectesException, FileReader, Land, Parser}

class MainTest extends AnyFunSuite {

  test(
    "A wrong character in the file should throw an Exception"
  ) {
    val content: List[String] = FileReader.get_file_content("./ko.txt")
    assertThrows[DonneesIncorrectesException](
      Parser.get_mowers_from_list(content, Land(5, 5))
    )
  }

  test("When is not found it should throw an Exception") {
    assertThrows[DonneesIncorrectesException](
      FileReader.get_file_content("./OMOFDOFOFO.txt")
    )
  }

  test("An existing file should be opened") {
    val content: List[String] = FileReader.get_file_content("./ok.txt")
    assert(content === List("5 5", "1 2 N", "GAGAGAGAA"))
  }

  test("Land can be parsed") {
    val content: List[String] = FileReader.get_file_content("./ok.txt")
    val land: Land = Parser.get_land_from_list(content)
    assert(land === Land(5, 5))
  }

  test("Mowers can be parsed") {
    val content: List[String] = FileReader.get_file_content("./ok.txt")
    val land: Land = Parser.get_land_from_list(content)
    val mowers: List[Mower] = Parser.get_mowers_from_list(content, land)
    val res = List(new Mower(Coordinates(1, 2, 'N'), "GAGAGAGAA", land))
    assert(mowers(0).start_coordinates.x == res(0).start_coordinates.x)
    assert(mowers(0).start_coordinates.y == res(0).start_coordinates.y)
    assert(
      mowers(0).start_coordinates.direction == res(0).start_coordinates.direction
    )
    assert(mowers(0).sequence == res(0).sequence)
    assert(mowers(0).land.size_y == res(0).land.size_y)
    assert(mowers(0).land.size_x == res(0).land.size_x)
  }

  test("Mower can't go under x=0") {
    val mower = new Mower(Coordinates(0, 0, 'W'), "A", Land(5, 5))
    assert(
      mower.move(mower.start_coordinates, mower.sequence(0)) == Coordinates(
        0,
        0,
        'W'
      )
    )
  }

  test("Mower can't go under y=0") {
    val mower = new Mower(Coordinates(0, 0, 'S'), "A", Land(5, 5))
    assert(
      mower.move(mower.start_coordinates, mower.sequence(0)) == Coordinates(
        0,
        0,
        'S'
      )
    )
  }

  test("Mower can't go over the x-land") {
    val mower = new Mower(Coordinates(5, 5, 'E'), "A", Land(5, 5))
    assert(
      mower.move(mower.start_coordinates, mower.sequence(0)) == Coordinates(
        5,
        5,
        'E'
      )
    )
  }
  test("Mower can't go over the y-land") {
    val mower = new Mower(Coordinates(5, 5, 'N'), "A", Land(5, 5))
    assert(
      mower.move(mower.start_coordinates, mower.sequence(0)) == Coordinates(
        5,
        5,
        'N'
      )
    )
  }

  test("Mower can move normally") {
    val mower = new Mower(Coordinates(5, 4, 'N'), "GDA", Land(5, 5))
    assert(mower.run_sequence() == Coordinates(5, 5, 'N'))
  }

  test("Mowers can move") {
    val land = Land(5, 5)
    val mower = new Mower(Coordinates(5, 4, 'N'), "GDA", land)
    val list = List(mower)
    val mowerManager = MowerManager(list, land)
    assert(
      mowerManager.run_all_mowers() == List(
        MowerEnded(land, "GDA", Coordinates(5, 4, 'N'), Coordinates(5, 5, 'N'))
      )
    )
  }

  test("Json output correct") {
    val json =
      """{"debut":{"point":{"x":1,"y":2},"direction":"N"},"instructions":["G","A","G","A","G","A","G","A","A"],"fin":{"point":{"x":1,"y":3},"direction":"N"}}"""
    val response: String = Jsonner
      .mowerEndedToJson(
        MowerEnded(
          Land(5, 5),
          "GAGAGAGAA",
          Coordinates(1, 2, 'N'),
          Coordinates(1, 3, 'N')
        )
      )
      .toString()
    assert(response == json)
  }

  test("Can't create a Land if x or y < 0 ") {
    assertThrows[DonneesIncorrectesException](
      Parser.check_land("-1", "-1")
    )
  }

  test("Can't create a Land if x or y is a letter ") {
    assertThrows[DonneesIncorrectesException](
      Parser.check_land("A", "A")
    )
  }

  test("Can't create a mower if it's out of land bound") {
    val land = Land(5, 5)
    assertThrows[DonneesIncorrectesException](
      Parser.check_mower(Coordinates(6, 6, 'N'), "A", land)
    )
  }

  test("Can't create a mower if wrong sequence") {
    val land = Land(5, 5)
    assertThrows[DonneesIncorrectesException](
      Parser.check_mower(Coordinates(4, 4, 'N'), "P", land)
    )
  }

  test("Can't create a mower if wrong direction") {
    val land = Land(5, 5)
    assertThrows[DonneesIncorrectesException](
      Parser.check_mower(Coordinates(4, 4, 'P'), "A", land)
    )
  }

  test("Can't create a mower if x or y < 0") {
    val land = Land(5, 5)
    assertThrows[DonneesIncorrectesException](
      Parser.check_mower(Coordinates(-1, -1, 'N'), "A", land)
    )
  }

  test("Can't create a mower sequence with number") {
    val land = Land(5, 5)
    assertThrows[DonneesIncorrectesException](
      Parser.check_mower(Coordinates(-1, -1, 'N'), "A555", land)
    )
  }
}
