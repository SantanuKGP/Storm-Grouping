import scala.io.Source.fromFile

object Helper extends App{
  val path = "dictionary.txt"
  println(fromFile(path).getLines().toList.map("\""+ _ + "\""))
}
