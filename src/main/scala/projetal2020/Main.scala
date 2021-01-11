package projetal2020

object Main extends App {

  val content: List[String] = FileReader.get_file_content("./ok.txt")
  val land: Land = Parser.get_land_from_list(content)
  val mowers: List[Mower] = Parser.get_mowers_from_list(content, land)
  val mower_manager: MowerManager = new MowerManager(mowers, land)
  val mowers_ended: List[MowerEnded] = mower_manager.run_all_mowers()
  val output_json: String = Jsonner.runToJson(land, mowers_ended)

  println(output_json)
}
