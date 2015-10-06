import scala.io.Source
import scala.collection.mutable.ListBuffer

package edu.wmich.cs3310.Guo.Assg1

import java.io._
import scala.reflect.{BeanProperty, BooleanBeanProperty}
//remove if not needed
import scala.collection.JavaConversions._

class UIinput {

  private var inFileName: String = "TransData.txt"

  private var input: FileReader = new FileReader(inFileName)

  private var inFile: BufferedReader = new BufferedReader(input)

  @BeanProperty
  var n: Int = 0

  @BeanProperty
  var transCode: String = _

  @BeanProperty
  var countryCode: String = _

  @BeanProperty
  var restOfData: String = _

  def input1User(): Boolean = {
    val theLine = inFile.readLine()
    if (theLine != null) {
      cleanup(theLine)
      if (!(transCode == "%")) n += 1
      true
    } else false
  }

  private def cleanup(theLine: String) {
    transCode = theLine.substring(0, 1)
    if (transCode == "%") {
    } else if (transCode == "S") {
      countryCode = theLine.substring(2, 5)
    } else if (transCode == "A") {
    } else if (transCode == "I") {
      val field = theLine.substring(2, theLine.length).split(",")
      countryCode = field(1)
      restOfData = field(0) + "," + field(2) + "," + field(3) + "," + field(4) + 
        "," + 
        field(5) + 
        "," + 
        field(6)
    } else if (transCode == "D") {
      countryCode = theLine.substring(2, 5)
    }
  }

  def finishUp() {
    input.close()
    inFile.close()
  }
}





