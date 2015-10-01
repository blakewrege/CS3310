import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc


/* 
 * Note: in scala you can't overload classes only functions
 * I had to use some improvised solutions here.
 */


class DataStorage(val UIoutput: UIoutput) {
  
  private val test = "hi";
 // val newptrstruct = new ptrstruct(ptrstruct.list+1)
  
  

  
  
  def fromraw(filedata: Iterator[String]): Unit = {
    val fileLines = filedata.toList
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

    scala.tools.nsc.io.File("Backup.csv").writeAll(sortedList.mkString(","))

  }
  
  
  
  
  
  

  def frombackup: String = {
    val databackup = Source.fromFile("Backup.csv", "ISO-8859-1").getLines.mkString(",")
    return databackup
  }
  
  def insert(args: List[_]): Unit ={
    


    
val test = new Person()
if (test.i < 1){
println(test.i)
}else{
 println(test.addtolist(args))
}
    
    
    
    
    
    
  }





  def BSTsearch(args: List[_], command: String): List[_] = {
    val parsecode = command.split(" ")
    val item = parsecode(1).toUpperCase()
    val itemval = (item.toList.head.toDouble - 64)
    val nums = 0 to args.length
    var first = 1;
    var last = args.length;
    var middle = first + (first + last) / 2;
    var count = 0;

    if (itemval < 0 || itemval > 26) {
      val result =
        return List("invalid", "0")
    } else {
      while (first <= last) {

        count = count + 1

        if (middle == 3 && last == 2) {
          middle = 0;
        } else if (middle == 25 && args(middle).toString().compareTo(item) == -2) {
            return List("invalid", count)
        } else {
          middle = first + (last - first) / 2;
        }

        if (args(middle) == item) {
          if (0 == args(middle).toString().compareTo(item)) {


            return List("valid", count)
          } else {
            return List("invalid", count)
          }
          first = last + 1;

        } else if (args(middle).toString().compareTo(item)
          < 0) {
          first = middle + 1;
        } else {
          last = middle - 1;
        }
      }
    }
    return List("invalid", count)
  }

}

  
object Person {
    private var current = 0
    private val codearray = new ListBuffer[List[_]]()
    private def inc = {current += 1; current} 
    private def appender(args: List[_]) = { codearray += args; codearray   }
 
    
    //  def createPerson() = new Person()
}
 
class Person {
    val i = Person.inc

    def addtolist(args: List[_]): List[_] = {
    return Person.appender(args).toList
  } 


}
  
  

















  