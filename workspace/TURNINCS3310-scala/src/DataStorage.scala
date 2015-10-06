import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc
import scala.annotation.tailrec
import scala.collection.JavaConversions._

/* 
 * Note: in scala you can't overload classes only functions
 * I had to use some improvised solutions here.
 */

class DataStorage(val UIoutput: UIoutput) {
  private var output: FileWriter = new FileWriter("Backup.csv")
  private var outFile: BufferedWriter = new BufferedWriter(output)
  private var input: FileReader = _
  private var inFile: BufferedReader = _
  private var Search: Array[handler] = new Array[handler](200)
  private var rootPtr: Int = -1
  private var n: Int = 0
  private var nextEmptyPtr: Int = 0
  private var i: Int = _
  private var parentI: Int = _
  private var index: Int = _




// This overloaded function is for UserApp
  def this(filename: String, uioutput: UIoutput) {
    this()
    this.input = Source.fromFile(filename, "ISO-8859-1").getLines.toList
    this.uioutput = uioutput
    inFile = inpur
    Search = Array.ofDim[handler](200)
    index = 0
    var newLine = inFile.toString
    var field = newLine.split(",")
    rootPtr = java.lang.Integer.parseInt(field(0))
    n = java.lang.Integer.parseInt(field(1))
    nextEmptyPtr = java.lang.Integer.parseInt(field(2))
    while ((newLine = inFile.readLine()) != null) {
      field = newLine.split(",")
      Search(index) = new handler(field(2), field(3) + "," + field(4) + "," + field(5) + "," + field(6) + 
        "," + 
        field(7) + 
        "," + 
        field(8), java.lang.Integer.parseInt(field(0)), java.lang.Integer.parseInt(field(1)))
      index += 1
    }
  }


// Selects through UserApp
  def select(countryCode: String) {
    var count = 1
    i = rootPtr
    while ((i != -1) && (!(countryCode == Search(i).countryCode))) {
      i = if (countryCode.compareTo(Search(i).countryCode) < 0) Search(i).lchPtr else Search(i).rchPtr
      count += 1
    }
    if (i == -1) {
      uioutput.displayThis("S " + countryCode + " >> " + "invalid country code " + 
        "[visited " + 
        count + 
        " Nodes]" + 
        "\n")
    } else {
      if ((Search(i).id == "000")) {
        uioutput.displayThis("S " + countryCode + " >> " + "invalid country code " + 
          "[visited " + 
          count + 
          " Nodes]" + 
          "\n")
      } else {
        uioutput.displayThis("S " + countryCode + " >> " + "[visited " + count + " Nodes]")
        uioutput.displayThis("CDE ID- NAME-------------------- CONTINENT---- -------AREA ---POPULATION LIFE")
        uioutput.displayThis(Search(i))
        uioutput.displayThis("")
      }
    }
  }




// Deletes whatever is sent from UseApp
    def delete(countryCode: String) {
    var count = 1
    i = rootPtr
    while ((i != -1) && (!(countryCode == Search(i).countryCode))) {
      count += 1
      i = if (countryCode.compareTo(Search(i).countryCode) < 0) Search(i).lchPtr else Search(i).rchPtr
    }
    if (i == -1) {
      uioutput.displayThis("D " + countryCode + " >> " + "invalid country code " + 
        "[visited " + 
        count + 
        " Nodes]" + 
        "\n")
    } else {
      if (Search(i).id == "000") {
        uioutput.displayThis("D " + countryCode + " >> " + "invalid country code " + 
          "[visited " + 
          count + 
          " Nodes]" + 
          "\n")
      } else {
        Search(i).id = "000"
        uioutput.displayThis("D " + countryCode + " >> " + "OK, " + Search(i).name + " deleted " + 
          "[visited " + 
          count + 
          " Nodes]" + 
          "\n")
      }
    }
  }




 // My BST insert algorthim 

  def insert(args: List[_]): Unit = {
  val countryCode = args(1).toString
  val restOfData = List(args(0),args(2),args(3),args(5),args(7),args(8)).toString

    if (output != null) {
      Search(nextEmptyPtr) = new handler(countryCode, restOfData)

      Search(nextEmptyPtr).lchPtr = -1
      Search(nextEmptyPtr).rchPtr = -1
      var LorR = true
      if (rootPtr == -1) {
        rootPtr = nextEmptyPtr
      } else {
        i = rootPtr
        while (i != -1) {
          parentI = i
          if (countryCode.compareTo(Search(parentI).countryCode) < 0) {
            i = Search(i).lchPtr
            LorR = true
          } else {
            i = Search(i).rchPtr
            LorR = false
          }
        }
        if (LorR == true) {
          Search(parentI).lchPtr = nextEmptyPtr
        } else {
          Search(parentI).rchPtr = nextEmptyPtr
        }
      }
      n += 1
      nextEmptyPtr += 1
    } else {
      var count = 1
      Search(nextEmptyPtr) = new handler(countryCode, restOfData)

      Search(nextEmptyPtr).lchPtr = -1
      Search(nextEmptyPtr).rchPtr = -1
      var LorR = true
      if (rootPtr == -1) {
        rootPtr = nextEmptyPtr
        uioutput.displayThis("I " + Search(nextEmptyPtr).countryCode + "," + Search(nextEmptyPtr).restOfData)
        uioutput.displayThis("OK, " + Search(nextEmptyPtr).name + " inserted [visited " + 
          count + 
          " Nodes]\n")
      } else {
        i = rootPtr
        while (i != -1) {
          parentI = i
          if (countryCode.compareTo(Search(parentI).countryCode) < 0) {
            i = Search(i).lchPtr
            LorR = true
          } else {
            i = Search(i).rchPtr
            LorR = false
          }
          count += 1
        }
        if (LorR == true) {
          Search(parentI).lchPtr = nextEmptyPtr
          uioutput.displayThis("I " + Search(nextEmptyPtr).countryCode + "," + Search(nextEmptyPtr).restOfData)
          uioutput.displayThis("OK, " + Search(nextEmptyPtr).name + " inserted [visited " + 
            count + 
            " Nodes]\n")
        } else {
          Search(parentI).rchPtr = nextEmptyPtr
          uioutput.displayThis("I " + Search(nextEmptyPtr).countryCode + "," + Search(nextEmptyPtr).restOfData)
          uioutput.displayThis("OK, " + Search(nextEmptyPtr).name + " inserted [visited " + 
            count + 
            " Nodes]\n")
        }
      }
      n += 1
      nextEmptyPtr += 1
    }
  }
// Displays all in order
 def all() {
    uioutput.displayThis("A")
    uioutput.displayThis("CDE ID- NAME-------------------- CONTINENT---- -------AREA ---POPULATION LIFE")
    inOrderTrans(rootPtr)
    uioutput.displayThis("===========================\n")
  }

// Finshup function to write to Backup.csv
// Note: scala io doesn't open and close 

  def finishup = {


    scala.tools.nsc.io.File("Backup.csv").writeAll("rootPtr + "," + n + "," + nextEmptyPtr + "\n"")
    for (i <- 0 until n){

      scala.tools.nsc.io.File("Backup.csv").appendAll(Search(i).lchPtr + "," + Search(i).rchPtr + "," + 
              Search(i).countryCode + 
              "," + 
              Search(i).restOfData + 
              "\n")
    }

    }




  private def inOrderTrans(rootPtr: Int) {
    if (rootPtr == -1) {
    } else {
      inOrderTrans(Search(rootPtr).lchPtr)
      if (Search(rootPtr).id == "000") {
      } else {
        uioutput.displayThis(Search(rootPtr))
      }
      inOrderTrans(Search(rootPtr).rchPtr)
    }
  }

 

}




  
  

















  
