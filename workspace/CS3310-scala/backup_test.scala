import scala.io.Source
import scala.collection.mutable.ListBuffer

/**
 * @author blake
 */

object test {

  def printList(args: List[_]): Unit = {
    args.foreach(println)
  }

  def main(args: Array[String]) {

    println("Following is the content read:")

    val filename = "RawData.csv"
    val fileLines = Source.fromFile(filename, "ISO-8859-1").getLines.toList
    var count = 0
    val codearray = new ListBuffer[String]()
    val valuearray = new ListBuffer[Int]()
    while (count < (fileLines.length)) {

      val words = fileLines(count).split(",") 
      codearray += words(1) 
      valuearray += words(1).toList.head.toInt - 64
      count = count + 1;

    }
    val codeList = codearray.toList
    val valueList = valuearray.toList
    val sortedList = codeList.sortWith(_ < _)
   printList(sortedList)
//    printList(valueList)
    
    

    
    //BST
    //change item to test
  val item = "ATZ"  
  val itemval = item.toList.head.toInt - 64
  val nums = 1 to 26   
  var first = 1;
  var last = count;
  var middle = first + (first+last)/2;

  while(first<=last)                                                                           
  {
      (first,"  ","  ",middle,"  ",last).productIterator.foreach(print)
    println()
 //   first=first+1  
     
     
      if(middle==3&&last==2)                                                                      
      {
        middle = 0;
      }
        
      else
      {
        middle = first + (last-first)/2;
      }     
    
      if(nums(middle)==itemval)                                                                        
      {
        if (item==sortedList(0)) {
          
        
       println() 
       print("DONE")
       println() 
      (first,"  ","  ",middle,"  ",last).productIterator.foreach(print)
     

       }else{
          printf("\nCountry Code not found");
       }
             first=last+1; 
        
      }
         
      
      else if(nums(middle)<itemval)
      {
        first = middle+1;
      }
      else
      {
        last = middle-1;
      }
   }
        
  }
}



  

  
  
  
  