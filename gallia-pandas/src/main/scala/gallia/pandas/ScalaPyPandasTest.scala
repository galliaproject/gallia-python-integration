package gallia
package pandas

// ===========================================================================
object ScalaPyPandasTest {
 
  val People: List[Seq[Any]] = List(Seq("john", "34", "Sydney"), Seq("Jane", "30", "Delhi"))

  def main(args: Array[String]): Unit = {
    val df = ScalaPyPandas.dataFrame(Seq("name", "age", "city"), People)

    assert(df.toString.trim == """
   name age    city
0  john  34  Sydney
1  Jane  30   Delhi  
""".trim)
  }
  

}

// ===========================================================================
