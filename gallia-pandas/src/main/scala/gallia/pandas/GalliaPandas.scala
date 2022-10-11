package gallia
package pandas

import aptus._
import scala.util._

import me.shadaj.scalapy.readwrite.Reader
import me.shadaj.scalapy.interpreter.Platform

// ===========================================================================
object GalliaPandas { import PandasSchema._

  def pandasDataFrameToHeadS(df: PyDynamic): HeadZ = {
    val pandasSchema = PandasSchemaParser(df)    

    AObjs(
        pandasSchema.cls,
        data(pandasSchema)(df))    
  }

  // ===========================================================================
  private def data(pandasSchema: Cls2)(df: PyDynamic): Objs = {
      val readers: Vector[Reader[_]] = pandasSchema.readers
      var currColumnIndex: Int = 0   

      // ---------------------------------------------------------------------------
      implicit val reader = // must rely on .as[Any] mechanism because we can't use reader.read without access to .value or .rawValue
        new Reader[AnyValue] {
          override def readNative(r: Platform.Pointer): AnyValue = {
            readers(currColumnIndex).readNative(r) }}

      // ---------------------------------------------------------------------------
      val columnNames: Seq[SKey] = pandasSchema.fields.map(_.name)
      val itertuples: PyDynamic = df.itertuples() // TODO: t220331185621 - confirm no need to close it?
  
      // ---------------------------------------------------------------------------
      Iterator
        .continually {
          Try { // will throw PythonException: <class 'StopIteration'> when done      
            val row = ScalaPyUtils.pythonTupleToScalaSeq(
                // eg: Pandas(Index=0, year=1949, month='Jan', passengers=112)
                itertuples.__next__())
             
            columnNames
              .zipWithIndex
              .map { case (name, index) =>   
                currColumnIndex = index          
                name ->
                  optionalValue(row.apply(index + 1 /* 1-based */)) }
              .pipe(gallia.obj) } }
      .takeWhile(successful)
      .map(_.get)
      .pipe { data => // TODO: t220331185621 - confirm no need to close it?
        new DataRegenerationClosure[Obj] {
          def regenerate: () => aptus.CloseabledIterator[Obj] =
            () => aptus.CloseabledIterator.fromUncloseable(data) } }
      .pipe(Objs.from)
    }

    // ===========================================================================
    private def optionalValue(value: PyDynamic)(implicit ev: Reader[AnyValue]): Option[AnyValue] =
        if (isPandasNull(value)) None
        else                     Some(value.as[AnyValue])  

    // ---------------------------------------------------------------------------
    private def successful(tried: Try[Obj]): Boolean = 
      tried match {          
        case Success(_) => true 
        case Failure(f) =>
          if (f.getMessage.contains("class 'StopIteration'")) false              
          else                                                illegalState(f) }
  
}

// ===========================================================================
