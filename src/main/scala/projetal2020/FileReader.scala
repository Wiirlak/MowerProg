package projetal2020

object FileReader {
  import better.files._

  def get_file_content(path: String): List[String] = {
    val f: File = File(path)
    // TODO : File not found ?
    f.lines.toList
  }
}
