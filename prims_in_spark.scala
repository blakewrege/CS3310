import spark.SparkContext
import SparkContext._
import util.control.Breaks._

object Prims {

  def PrimsMap(row: String): List[(String,String)] = {    
    val nodeId = row.split(" ")(0); val edge = row.split(" ")(1); var newEdge = ""
    var mapOutput = List[(String, String)]()

    if (nodeId == "-1") mapOutput = (nodeId, edge) :: mapOutput
    else if (edge != "" && edge != ",") {
      val edgeList = edge.split(",").sortBy(x => x.split(":")(1).toDouble)
      mapOutput = ("-1", nodeId + "=>" + edgeList(0)) :: mapOutput
      if (edgeList.length > 1) newEdge = edgeList.filter(_.contains(edgeList(0)) == false).reduce(_ + "," + _)
      mapOutput = (nodeId, newEdge) :: mapOutput          
    }
    return mapOutput
  }    

  def PrimsReduce(rows: Seq[String]): List[String] = {
    var mstEdge = List[String](); var mstWeight = 0.0
    var weight = 0.0
    var reduceOutput = List[String]()

    if (rows.toString().contains("=>") == false) reduceOutput = rows(0) :: reduceOutput
    else {
      val mstList = rows.filter(_.contains("mst"))
      val edgeList = rows.filter(_.contains("=>")).sortBy(x => x.split(":")(1))
      if (mstList.length != 0) {
        mstEdge = mstList.filter(_.contains("mstEdge"))(0).split("_")(1).split(",").toList
        mstWeight = mstList.filter(_.contains("mstWeight"))(0).split("_")(1).toDouble
      }
       
      breakable {
        for (i <- 0 to (edgeList.length - 1)) {
          if (mstEdge.length == (edgeList.length - 1)) break        
          val srcNode = edgeList(i).split("=>")(0)
          val dstNode = edgeList(i).split("=>")(1).split(":")(0)
          weight = edgeList(i).split("=>")(1).split(":")(1).toDouble
          if (mstEdge.map(x => x.split("-")(0)).contains(dstNode) == false) {
            mstEdge = srcNode + "-" + dstNode :: mstEdge
            mstWeight += weight
          }                            
        }      
      }      
      reduceOutput = "mstEdge_" + mstEdge.reduce(_ + "," + _) :: reduceOutput
      reduceOutput = "mstWeight_" + mstWeight.toString() :: reduceOutput
    }
    return reduceOutput    
  }  

  def main(args: Array[String]) {
    val sc = new SparkContext(args(0), "Prims")
    var iterativeInput = sc.textFile("hdfs://br9/user/root/roadNetwork.txt", 88)

    val totalNode = iterativeInput.count().toInt
    var totalEdge = 0
    var iteration = 0       

    while (totalEdge < (totalNode - 1)) {
      val mapResult = iterativeInput.flatMap(x => PrimsMap(x)).groupByKey(88).cache()
      val reduceResult = mapResult.mapValues(x => PrimsReduce(x)).cache()
      iterativeInput = reduceResult.flatMap { case (k, vs) => vs.map(v => (k+" "+ v)) }.cache()
      totalEdge = iterativeInput.filter(_.contains("mstEdge")).flatMap(x => x.split(" ")(1).split("_")(1).split(",")).count().toInt
      iteration += 1
    }    
    val MSTEdge = iterativeInput.filter(_.contains("mstEdge")).map(x => x.split(" ")(1))
    val MSTWeight = iterativeInput.filter(_.contains("mstWeight")).map(x => x.split(" ")(1))
    val output = MSTEdge.union(MSTWeight)
    output.saveAsTextFile("hdfs://br9/user/root/roadNetworkPrimsOutput")
    System.out.println("Total Number of Iterations: " + iteration.toString())
    System.exit(0)
  }
}