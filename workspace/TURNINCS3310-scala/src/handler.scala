
// Handler handles the stored Lists from insert
// *Note: Scala has different overloaded functions




class handler(var countryCode: String, var restOfData: String) {

    var lchPtr: Int = -1
    var rchPtr: Int = -1


  cleaner(restOfData)

  def this(countryCode: String, 
      restOfData: String, 
      lchPtr: Int, 
      rchPtr: Int) {
    this()
    this.countryCode = countryCode
    this.restOfData = restOfData
    cleaner(restOfData)
    this.lchPtr = lchPtr
    this.rchPtr = rchPtr
  }

  def cleaner(restOfData: String) {
    val field = restOfData.split(",").toList
    val id = field(0).toString
    val name = field(1).toString
    val continent = field(2).toString
    val area = field(3).toString
    val population = field(4).toString
    val life = field(5).toString
  }
}