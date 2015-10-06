import scala.io.Source
import scala.collection.mutable.ListBuffer

/**
 * @author Blake Wrege
 * CS3310 Assignment 1
 */

object Setup {

  def main(args: Array[String]) {
    val UIoutput = new UIoutput
    UIoutput.start("-->> SETUP started")
    val indata = new RawData("RawData.csv", UIoutput)

    val storedata = new DataStorage(UIoutput)
    var count = 0

    UIoutput.displayThis("\n-->> OPENED RawData file");
    UIoutput.displayThis("\n-->> OPENED Backup file");
    UIoutput.displayThis("\n-->> OPENED Log file");
  
    // Reading the file line by line

    while (count < indata.file.length) {

     storedata.insert(indata.readline(indata.file(count)))
      count = count + 1
    }



    UIoutput.displayThis("\n-->> CLOSED Log file");
    UIoutput.displayThis("\n-->> CLOSED Backup file");
    UIoutput.displayThis("\n-->> CLOSED Backup file")
    
    UIoutput.displayThis("\n-->> SETUP finished – inserted 26 countries into DataStorage")
    storedata.finishup
  }

  def printList(args: List[List[_]]): Unit = {
    args(0).foreach(println)
  }

}



  

  
  
  
  
