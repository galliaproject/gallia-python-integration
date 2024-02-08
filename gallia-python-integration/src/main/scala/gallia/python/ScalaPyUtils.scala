package gallia
package python

import me.shadaj.scalapy.py

// ===========================================================================
object ScalaPyUtils {
  import py.Dynamic.global
  import py.SeqConverters
  
  // ---------------------------------------------------------------------------
  lazy val collections = py.module("collections")

  // ===========================================================================
  def tostring(value: py.Dynamic) = me.shadaj.scalapy.py.Dynamic.global.str(value).as[String]

  // ===========================================================================
  def scalaAnyssToPyAny(values: Stream[Seq[Any]]): py.Any = values.map(scalaAnysToPyAny).toPythonProxy
  def scalaAnyssToPyAny(values: List  [Seq[Any]]): py.Any = values.map(scalaAnysToPyAny).toPythonProxy

    // ---------------------------------------------------------------------------
    def scalaAnysToPyAny(values: Seq[Any]): py.Any = values.map(scalaAnyToPyAny).toPythonProxy

      // ---------------------------------------------------------------------------  
      def scalaAnyToPyAny(value: Any): py.Any = // TODO: simpler way? 
        value match {
          case x: Boolean => x
          case x: String  => x
          case x: Int     => x
          case x: Double  => x
          
          case x: Byte    => x      
        //case x: Short   => x
          case x: Long    => x      
          case x: Float   => x
                
        //case x: BigInt   => x      
        //case x: BigDec   => x
        //case x: LocalDateTime => x
        }

  // ===========================================================================
  def pythonTupleToScalaSeq(tuple: py.Any): Seq[py.Any] = { // hopefully there's a simpler way...    
    val deque = collections.deque(tuple)
    
    import py.PyQuote

    val tmp = py"${tuple}[0]"

    if (true)
        Range(0, global.len(deque).as[Int])
          .map { _ => deque.popleft() }
          .toList
    else
      List(tmp)
  }

  // ---------------------------------------------------------------------------
  def pythonTupleToScalaSeq (tuple: py.Dynamic): Seq[py.Dynamic] = pythonTupleToScalaSeq2(tuple)
  def pythonTupleToScalaSeq2(tuple: py.Dynamic): Seq[py.Dynamic] = { // hopefully there's a simpler way...
    //t(0)         // Exception in thread "main" me.shadaj.scalapy.py.PythonException: <class 'TypeError'> 'tuple' object is not callable
    //for ((yy: py.Dynamic) <- tuple) yield { }
  
    if (false) {
      val e = global.enumerate(tuple)
      println(e.__next__())
      //???
      //TODO: try println(iter.__next__())
      val deque = collections.deque(tuple)

      Range(0, global.len(deque).as[Int])
        .map { _ => deque.popleft() }
        .toList
    } else {
      val iter = global.iter(tuple)
      
      Range(0, global.len(tuple).as[Int])
        .map { _ => iter.__next__() }
        .toList
//      val i = global.iter(tuple)
//      println(i.__next__())
//      println(i.__next__())
//      println(i.__next__())
//      println(i.__next__())
//      println(i.__next__()) // me.shadaj.scalapy.py.PythonException: <class 'StopIteration'>
//      ???
    }
  }

  // ---------------------------------------------------------------------------
  def pythonListToScalaSeq(list: py.Dynamic): Seq[py.Dynamic] = _pythonListToScalaSeq(len(list))(iter(list))

    private def _pythonListToScalaSeq(size: Int)(iter: py.Dynamic): Seq[py.Dynamic] =
      Range(0, size)
        .map { _ => iter.__next__() }
        .toList

  def len (lengthable: py.Dynamic): Int        = global.len (lengthable).as[Int]
  def iter(iterable  : py.Dynamic): py.Dynamic = global.iter(iterable)
}

// ===========================================================================
