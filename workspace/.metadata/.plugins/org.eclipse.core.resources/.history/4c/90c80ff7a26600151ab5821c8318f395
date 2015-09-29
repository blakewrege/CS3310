import scala.io.Source
import scala.collection.mutable.ListBuffer


/**
 * @author blake
 */


object test {

  def main(args: Array[String]) {

    val indata = new RawData("RawData.csv")
    val outdata = new DataStorage(indata.file)

    //    printList(sortedList)
    //    printList(valueList)

    //BST
    //change item to test
    

  ///  BSTsearch(outdata.sortedList, "ATA")

  }

  def printList(args: List[_]): Unit = {
    args.foreach(println)
  }

  def BSTsearch(args: List[_], code: String): Unit = {

    var count = 0;
    val item = code
    val itemval = item.toList.head.toInt - 64
    val nums = 1 to args.length
    var first = 1;
    var last = args.length;
    var middle = first + (first + last) / 2;

    while (first <= last) {
      (first, "  ", "  ", middle, "  ", last).productIterator.foreach(print)
      println()
      count = count + 1
      //   first=first+1  

      if (middle == 3 && last == 2) {
        middle = 0;
      } else {
        middle = first + (last - first) / 2;
      }

      if (nums(middle) == itemval) {
        if (item == args(middle)) {

          println()
          print("DONE")
          println()
          (first, "  ", "  ", middle, "  ", last).productIterator.foreach(print)
          printf("\nNumber of nodes %d", count);
        } else {
          printf("\nCountry Code not found");
        }
        first = last + 1;

      } else if (nums(middle) < itemval) {
        first = middle + 1;
      } else {
        last = middle - 1;
      }
    }

  }

}



  

  
  
  
  
