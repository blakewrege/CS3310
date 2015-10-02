import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc


class UIoutput {
  
  def start(tolog: String): Unit = {
        scala.tools.nsc.io.File("Log.txt").writeAll(tolog)
  }

  def displayThis(tolog: String): Unit = {
        scala.tools.nsc.io.File("Log.txt").appendAll(tolog)
}

  
  
  
  
  
}