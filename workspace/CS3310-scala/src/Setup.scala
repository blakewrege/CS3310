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
    while (count < indata.file.length){
     outdata.insert(indata.readline(indata.file(count)))
     //println(indata.readline(indata.file(count)))
     count = count + 1
    }


 
    
    
    
    
    
    
    
    
//        outdata.fromraw(indata.file)
  
    val dim: List[List[Int]] =
   List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
   )
   
  //printList(dim)
  
      UIoutput.displayThis("\n-->> SETUP finished – inserted 26 countries into DataStorage") 
  
  }

  def printList(args: List[List[_]]): Unit = {
    args(0).foreach(println)
  }

 

}



  

  
  
  
  
