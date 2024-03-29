trait BareBonesBinarySearchTree {
  def insert(key:Int)
  def has(key:Int):Boolean
  def inOrderTraversal():Unit
}

/** Simple implementation of a Binary Search Tree. */
class BinarySearchTree {
 
  /* Our Root Node. Empty to start with. */
  var rootNode: Option[Node] = None
 
  /* Inserts a value i if it does not already exist. */
  def insert(k: String) = {
    var i = k.toInt
    /* Require optimization or a warning. */
    //@tailrec
    def findSuitableParent(i: Int, o: Option[Node], p: Option[Node]): Option[Node] = {
     
      o match {
        case None => return p
        case Some(n) if (n.key > i) => findSuitableParent(i, n.left, o)
        case Some(n) if (n.key < i) => findSuitableParent(i, n.right, o)
        case Some(n) if (n.key == i) => None
      }
    }
 
    /* Check for a root node, if none make this the root node else find a parent. */
    rootNode match {
      case None => rootNode = Some(Node(i))
      case Some(node) => {
        val p = findSuitableParent(i, rootNode, None);
        p match {
          case None => // duplicate
          case Some(n) if (n.key > i) => n.left = Some(Node(key = i, parent = p))
          case Some(n) if (n.key < i) => n.right = Some(Node(key = i, parent = p))
        }
      }
    }
  }
 
  /* Function returning whether a given value exists. */
  def has(i: Int): Boolean = {
    //@tailrec
    def _has(value: Int, oNode: Option[Node], seen: List[Node]): Boolean = {
      oNode match {
        case None => return false
        case Some(node) if (node.key == value) => return true;
        case Some(node) if (node.key > value) => _has(value: Int, node.left, node :: seen)
        case Some(node) if (node.key < value) => _has(value: Int, node.right, node :: seen)
      }
    }
 
    /* Is there a root node? If not then we don't have value i else traverse and locate. */
    rootNode match {
      case None => return false
      case Some(node) => return _has(i, rootNode, List())
    }
  }
 
  def inOrderTraversal = {
    /* Look left of current node then look at node then look right. */
    
    def _traverse(n: Option[Node]): Unit = {
      var count =0 
      count = count +1
      n match {
        case None =>
        case Some(node) => print(node.parent,_traverse(node.left)); println("This node: " + node.key); _traverse(node.right)
      }
    }
    /* Starting at root node. */
    _traverse(rootNode)
  }
}
 
case class Node(key: Int, var left: Option[Node] = None, var right: Option[Node] = None,
  var parent: Option[Node] = None)