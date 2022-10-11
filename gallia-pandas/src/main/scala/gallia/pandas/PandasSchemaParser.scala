package gallia
package pandas

import aptus._

// ===========================================================================
object PandasSchemaParser { import PandasSchema._
  
  // ---------------------------------------------------------------------------
  def apply(df: PyDynamic): Cls2 = {
    val keys   = ScalaPyUtils.pythonListToScalaSeq(df.columns.values.tolist()) // TODO: confirm ordered
    val dtypes = ScalaPyUtils.pythonListToScalaSeq(df.dtypes)                  // TODO: confirm ordered    

    keys
      .zipSameSize(dtypes)
      .map { case (key, dataType) =>                        
        Fld2(
          name     = key.as[String], 
          dataType = ScalaPyUtils.tostring(dataType).pipe(DataType2.withName)) }
      .pipe(Cls2.apply)
  }
  
}

// ===========================================================================
