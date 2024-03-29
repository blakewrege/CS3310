import scala.io.Source
import scala.collection.mutable.ListBuffer

object UserApp {

  def main(args: Array[String]) {
    val UIoutput = new UIoutput
    UIoutput.displayThis("\n-->> USERAPP started")

    val rawdata = new DataStorage(UIoutput)
    val input = new UIinput(UIoutput)

    val translist = input.setup("TransData.txt")

    var count = 0
      UIoutput.displayThis("\n-->> OPENED Backup file")
    while (count < (translist.length - 1)) {
      val transaction = input.filterList(translist(count).toString(), rawdata.frombackup.split(",").toList)
      readtransaction(transaction)
      count = count + 1

    }
          UIoutput.displayThis("\n-->> CLOSED Backup file")

    UIoutput.displayThis("\n-->> USERAPP finished – processed " + count + " transactions")

    def printList(args: List[_]): Unit = {
      args.foreach(println)
    }

    def readtransaction(args: List[_]): Unit = {
      if (args(0).toString().compareTo("comment") == 0) {

      } else if (args(0).toString().compareTo("invalid") == 0) {

        UIoutput.displayThis("\n" + args(2) + " >> " + args(0) + " [visited " + args(1) + " nodes]")
      } else {
        UIoutput.displayThis("\n" + args(2) + " >> [visited " + args(1) + " nodes]")
      }
    }

  }

}

