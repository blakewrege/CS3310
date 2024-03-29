import scala.io.Source
import scala.collection.mutable.ListBuffer

/**
 * @author blake
 */

object Setup {

  def main(args: Array[String]) {
    val UIoutput = new UIoutput
    UIoutput.start("-->> SETUP started")
    val indata = new RawData("RawData.csv", UIoutput)

    val outdata = new DataStorage(UIoutput)
    var count = 0

    val lines = new ListBuffer[String]()

    //    while (count < indata.file.length){
    //
    //     println(indata.readline(indata.file(count)))
    //     count = count + 1
    //    }

    UIoutput.displayThis("\n-->> OPENED Backup file")

    outdata.fromraw(indata.file)
    UIoutput.displayThis("\n-->> CLOSED Backup file")

    UIoutput.displayThis("\n-->> SETUP finished – inserted 26 countries into DataStorage")

  }

  def printList(args: List[List[_]]): Unit = {
    args(0).foreach(println)
  }

}



  

  
  
  
  
