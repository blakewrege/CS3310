import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc
import scala.annotation.tailrec

/* 
 * Note: in scala you can't overload classes only functions
 * I had to use some improvised solutions here.
 */

class DataStorage(val UIoutput: UIoutput) {

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

 
  
  
  

//my attempts to make a BST, if I had more time I would restart this whole process
  
  
  
  
  
  
  
  
  
  
//  
//    def finshup(filedata: Iterator[String]): Unit = {
//    val fileLines = filedata.toList
//    var count = 0
//    val codearray = new ListBuffer[String]()
//    val valuearray = new ListBuffer[Int]()
//    while (count < (fileLines.length)) {
//      val words = fileLines(count).split(",")
//      codearray += words(1)
//      valuearray += words(1).toList.head.toInt - 64
//      count = count + 1;
//    }
//
//    val codeList = codearray.toList
//    val valueList = valuearray.toList
//
//    scala.tools.nsc.io.File("Backup.csv").writeAll(sortedList.mkString(","))
//
//  }
  
  
//  def insert(args: List[_]): Unit = {
//
//    val test = new handler()
//    val rawdat = test.addtolist(args)
//   
//
//    val item = rawdat(test.i - 1).toList(1).toString().toUpperCase()
//
//    val itemval = (item.toList.head.toDouble - 64)
//    val nums = 0 to test.i - 1
//    var first = 1;
//    var last = test.i - 1;
//    var middle = first + (first + last) / 2;
//    var count = test.i - 1;
//    if (count > 0) {
//       println(rawdat(test.i - 1).toList(1)," vs parent",rawdat(test.i - 2).toList(1))
//count=0
//      while (count < (test.i - 1)) {
//
//
//        if (rawdat(count).toList(1).toString().toUpperCase().compareTo(item)
//          < 0) {
//          first = middle + 1;
//          println("RIGHT")
//        } else {
//          last = middle - 1;
//          println("LEFT")
//        }
//count=count+1
//      }
//    }
//  }
//    
//  
//
//  def BSTsearch(args: List[_], command: String): List[_] = {
//    val parsecode = command.split(" ")
//    val item = parsecode(1).toUpperCase()
//    val itemval = (item.toList.head.toDouble - 64)
//    val nums = 0 to args.length
//    var first = 1;
//    var last = args.length;
//    var middle = first + (first + last) / 2;
//    var count = 0;
//
//    if (itemval < 0 || itemval > 26) {
//      val result =
//        return List("invalid", "0")
//    } else {
//      while (first <= last) {
//
//        count = count + 1
//
//        if (middle == 3 && last == 2) {
//          middle = 0;
//        } else if (middle == 25 && args(middle).toString().compareTo(item) == -2) {
//          return List("invalid", count)
//        } else {
//          middle = first + (last - first) / 2;
//        }
//
//        if (args(middle) == item) {
//          if (0 == args(middle).toString().compareTo(item)) {
//
//            return List("valid", count)
//          } else {
//            return List("invalid", count)
//          }
//          first = last + 1;
//
//        } else if (args(middle).toString().compareTo(item)
//          < 0) {
//          first = middle + 1;
//        } else {
//          last = middle - 1;
//        }
//      }
//    }
//    return List("invalid", count)
//  }
//
//}

// Handler handles the stored Lists from insert
}
object handler {
  private var current = 0
  private var lch = 0
  private var rch = 0
  private val codearray = new ListBuffer[List[_]]()
  private def inc = { current += 1; current }
    private def linc = { lch += 1; lch }
      private def rinc = { rch += 1; rch }
  private def appender(args: List[_]) = { codearray += args; codearray }

}

class handler {
  val i = handler.inc
  val linc =  handler.lch
   val rinc =  handler.lch

  def addtolist(args: List[_]): List[List[_]] = {
    return handler.appender(args).toList

  }

}
  
  

















  
