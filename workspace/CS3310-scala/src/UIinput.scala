import scala.io.Source
import scala.collection.mutable.ListBuffer

class UIinput(val UIoutput: UIoutput) {

  def setup(filename: String): List[_] = {
    UIoutput.displayThis("\n-->> OPENED TransData file")
    val transData = Source.fromFile(filename, "ISO-8859-1").getLines.toList
    UIoutput.displayThis("\n-->> CLOSED TransData file")
    return transData
  }
  

  //Case operators in Scala are called match
  
  def filterList(transline: String, rawList: List[_]): List[_] = {

    transline.take(1) match {
      case "A" => return Aswitch(transline, rawList)
      case "I" => return Iswitch(transline, rawList)
      case "S" => return Sswitch(transline, rawList) :+ transline.toUpperCase
      case "D" => return Dswitch(transline, rawList)
      case _ => return List("comment",0) :+ transline.take(1)
    }

  }

  def Aswitch(strTok: String, rawList: List[_]): Unit = {
    println("SHOW ALL")
  }

  def Iswitch(strTok: String, rawList: List[_]): Unit = {
    println("INSERT")
  }

  def Sswitch(strTok: String, rawList: List[_]): List[_] = {
        println(strTok)
    return BSTsearch(rawList, strTok)
    

  }

  def Dswitch(strTok: String, rawList: List[_]): Unit = {
    println("Delete")
  }
  
  
  
  
  // My original BST that works just isn't in the right spot

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

  def printList(args: List[_]): Unit = {
    args.foreach(println)
  }
}







