
import scala.collection.mutable.ListBuffer

/**
 * @author blake
 */
class Node2(id: Int) {
  
  
  
  
  
}

class Output
{
    var point: Double = _
    var solution: Array[Double] = Array(6)
}

object primsAlgo2 {
  var numNodes = 0
  var numEdges = 0

  def main(args: Array[String]) {
    var count = 0
    val rowArray = readCSV("NodeData.txt")
    val colArray = rowArray.transpose
    var nodeArray = new Array[Node2](numNodes)

    while (count < numNodes + 1) {

      nodeArray(count) = new Node2(count)
      count = count + 1
    }
    count = 0

//    while (count < colArray(0).length) {
//      nodeStruct(rowArray(count)(0).toInt).addEdge(nodeStruct(rowArray(count)(1).toInt), rowArray(count)(2).toInt)
//      nodeStruct(rowArray(count)(1).toInt).addEdge(nodeStruct(rowArray(count)(0).toInt), rowArray(count)(2).toInt)
//      count = count + 1
//    }
//    count = 0
//
//    val graph = nodeStruct.toList

    // generateMST(graph).sortWith(_ < _).foreach(println)



  }

  def readCSV(fileName: String): Array[Array[Double]] = {
    val bufferedSource = io.Source.fromFile(fileName)
    
    var matrix: Array[Array[Double]] = Array.empty
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim.toDouble)
      if(cols.length==3){
      matrix = matrix :+ cols
      }else{
       val headNode = line.split(",").toList
        numNodes = headNode(0).toInt
        numEdges = headNode(1).toInt
        
      }
    }
    bufferedSource.close
    return matrix
  }

}