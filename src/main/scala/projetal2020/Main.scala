package projetal2020

object Main extends App {

//  val land = new Land(5, 5)
//  val t = new Mower(1, 2, 'N', "GAGAGAGAA", land)
//  val m = new Mower(3, 3, 'E', "AADAADADDA", land)
//  val x = new Mower(3, 3, 'E', "AAA", land)
////  val error_sequence = new Mower(3, 3, 'E', "PPAAA", land)
//  val error_direction = new Mower(3, 3, 'M', "AAA", land)
  val content: List[String] = FileReader.get_file_content("./ok.txt")
  val land: Land = Parser.get_land_from_list(content)
  val mowers: List[Mower] = Parser.get_mowers_from_list(content, land)
  val mowm = new MowerManager(mowers, land)
  val mowerEndedList = mowm.run_all_mowers()

  println(Jsonner.runToJson(land, mowerEndedList))
//  print(mowers)

  // Le code suivant ne compilera pas.
  // var tmp = null;
  // var tmp2 = if (tmp == 1) "yes" else 1

  // println(s"tmp: $tmp, tmp2: $tmp2")
}
