package projetal2020

import java.io.FileNotFoundException
import better.files._

object FileReader {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def get_file_content(path: String): List[String] = {
    try {
      val f: File = File(path)
      f.lines.toList
    } catch {
      case _: FileNotFoundException =>
        throw DonneesIncorrectesException(s"File '${path}' not found")
      case _: Throwable =>
        throw DonneesIncorrectesException(s"File '${path}' can't be opened")
    }

  }
}
