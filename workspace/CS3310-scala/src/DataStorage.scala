import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc
import scala.annotation.tailrec

/* 
 * Note: in scala you can't overload classes only functions
 * I had to use some improvised solutions here.
 */

class DataStorage(val UIoutput: UIoutput) {

  def frombackup: String = {
    val databackup = Source.fromFile("Backup.csv", "ISO-8859-1").getLines.mkString(",")
    return databackup
  }

  // Provides a sorted backup since my BST is broken    

  def fromraw(filedata: List[String]): Unit = {
    val fileLines = filedata
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

  // My attempts to make a BST, if I had more time I would restart this whole process
  // Works on Setup but not on UserApp   

  def insert(args: List[_]): Unit = {

    val test = new handler()
    val rawdat = test.addtolist(args)
        test.setpointers(0, 0)
    if (test.i == 0) {
      test.setpointers(-1, -1)
    }

    val item = rawdat(test.i - 1).toList(1).toString().toUpperCase()

    val itemval = (item.toList.head.toDouble - 64)
    val nums = 0 to test.i - 1
    var first = 0;
    var last = test.i - 1
    var middle = first + (first + last) / 2
    var count = test.i - 1

    if (count > 0) {
      println(first,middle,last)
      count = 0
      println()
      while (first < (last)) {
        var middle = first + (first + last) / 2
        println()
        print(rawdat(test.i - 1).toList(1), " vs parent", rawdat(0).toList(1))

        if (rawdat(middle).toList(1).toString().toUpperCase().compareTo(item)
          < 0) {
          first = middle + 1
          test.addright(test.r)
          print("  RIGHT", test.r+1)
        } else {
          last = middle - 1
          test.addleft(test.l)
          print("  LEFT", test.l+1)
        }

         print("LEFT =" + (test.l+1).toInt,"  RIGHT =" + (test.r+1).toInt )
         println(first,middle,last)
        count = count + 1
      }
    }
    

  }
  def finishup = {
    val test = new handler()

    val megalist = test.printlist()
    var count = 0
    scala.tools.nsc.io.File("Backup.csv").writeAll("")
    while (count < megalist.length) {

      scala.tools.nsc.io.File("Backup.csv").appendAll(count + "," + megalist.toList(count).mkString(",") + "\n")
      count = count + 1

    }

  }
}

// Handler handles the stored Lists from insert
// *Note: Scala doesn't really have static variables

object handler {
  private var current = 0
  private var lch = 0
  private var rch = 0
  private val codearray = new ListBuffer[List[_]]()
  private def inc = { current += 1; current }
  private def linc = { lch += 1; lch }
  private def rinc = { rch += 1; rch }
  private def radd(): Int = { rch += 1; return rch }
  private def ladd(): Int = { lch += 1; return lch }
  private def set(lptr: Int, rptr: Int) = { lch = lptr; rch = rptr }
  private def appender(args: List[_]) = { codearray += args; codearray }
  private def printer(): List[List[_]] = { return codearray.toList }
}

class handler {
  val i = handler.inc
  val l = handler.linc
  val r = handler.rinc
  def addright(args: Int): Int = {
    return handler.radd
  }
  def addleft(args: Int): Int = {
    return handler.ladd
  }
  def setpointers(lptr: Int, rptr: Int): Unit = {
    return handler.set(lptr, rptr)
  }

  def addtolist(args: List[_]): List[List[_]] = {
    return handler.appender(args).toList

  }
  def printlist(): List[List[_]] = {
    return handler.printer

  }

}
  
  

















  
