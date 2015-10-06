import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

object UserApp {

  def main(args: Array[String]) {
    var transCode: String = null
    val input = new UIinput()
    val output = new UIoutput("open in append mode")
    val dataStorage = new DataStorage(new FileReader("Backup.csv"), output)
    output.displayThis("-->> USERAPP started")
    output.displayThis("-->> OPENED TransData file")
    output.displayThis("-->> OPENED Backup file")
    output.displayThis("-->> OPENED Log file\n")
    while (input.input1User()) {
      transCode = input.getTransCode
      transCode match {
        case "I" => dataStorage.insert(input.getCountryCode, input.getRestOfData)
        case "D" => dataStorage.delete(input.getCountryCode)
        case "S" => dataStorage.select(input.getCountryCode)
        case "A" => dataStorage.all()
        case "%" => //break
        case _ => println("Invalid transcode!")
      }
    }
    output.displayThis("-->> CLOSED Log file")
    output.displayThis("-->> CLOSED Backup file")
    output.displayThis("-->> CLOSED TransData file")
    output.displayThis("-->> USERAPP finished" + " - processed " + input.getN + 
      " transactions\n")
    dataStorage.finishUp()
    input.finishUp()
    output.finishUp()
  }
}
