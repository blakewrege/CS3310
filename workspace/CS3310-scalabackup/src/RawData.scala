import scala.io.Source

/* This reads the RawData.csv into memory. Note that Scala's I/O does not provide
 * a way to read line by line into memory. 
 * I mimicked the line by line style anyway
 * http://stackoverflow.com/questions/4458864/whats-the-right-way-to-use-scala-io-source
 */

class RawData(val filename: String, val UIoutput: UIoutput) {
  UIoutput.displayThis("\n-->> OPENED RawData file")
  val file = Source.fromFile(filename, "ISO-8859-1").getLines
  UIoutput.displayThis("\n-->> CLOSED RawData file")

  
   def readline (line: String): List[_] = {
    
 val parselist = line.split(",").toList 
    

  return List(parselist(0),parselist(1),parselist(2),
      parselist(3),parselist(5),parselist(7),parselist(8))
  }
}
