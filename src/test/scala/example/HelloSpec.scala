package example

import org.scalatest.funsuite.AnyFunSuite
import projetal2020.{DonneesIncorrectesException, FileReader, Land, Parser}

class HelloSpec extends AnyFunSuite {

  test(
    "A wrong character in the file should throw an Exception"
  ) {
    val content: List[String] = FileReader.get_file_content("./ko.txt")
    assertThrows[DonneesIncorrectesException](Parser.get_mowers_from_list(content, new Land(5,5)))
  }

}
