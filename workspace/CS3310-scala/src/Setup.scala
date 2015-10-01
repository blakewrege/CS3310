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
    outdata.fromraw(indata.file)
    
    
    UIoutput.displayThis("\n-->> SETUP finished – inserted 26 countries into DataStorage") 
  }

  def printList(args: List[_]): Unit = {
    args.foreach(println)
  }

 

}



  

  
  
  
  
