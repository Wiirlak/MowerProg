package projetal2020

object Main extends App {

//  val land = new Land(5, 5)
//  val t = new Mower(1, 2, 'N', "GAGAGAGAA", land)
//  val m = new Mower(3, 3, 'E', "AADAADADDA", land)
//  val x = new Mower(3, 3, 'E', "AAA", land)
////  val error_sequence = new Mower(3, 3, 'E', "PPAAA", land)
//  val error_direction = new Mower(3, 3, 'M', "AAA", land)
  val file_reader: FileReader = new FileReader()
  val content =
    file_reader.get_file_content("D:\\Ecole\\5AL\\Scalla\\Cours\\ok.txt")
  val parser = new Parser()
  val land = parser.get_land_from_list(content)
  val mower = parser.get_mower_from_list(content, land)
  print(mower)

  // Le code suivant ne compilera pas.
  // var tmp = null;
  // var tmp2 = if (tmp == 1) "yes" else 1

  // println(s"tmp: $tmp, tmp2: $tmp2")
}
