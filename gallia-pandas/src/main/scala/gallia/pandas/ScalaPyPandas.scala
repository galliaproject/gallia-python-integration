package gallia
package pandas

import me.shadaj.scalapy.py.SeqConverters

// ===========================================================================
object ScalaPyPandas {
  import gallia.python.ScalaPyUtils.scalaAnyssToPyAny

  // ===========================================================================
  def dataFrame(aobjs: AObjs)                              : PyDynamic = dataFrame(columns = aobjs.c.skeys, data = aobjs.rawData.toStream)
  def dataFrame(columns: Seq[String], data: List[Seq[Any]]): PyDynamic = dataFrame(columns,                 data = data.toStream)  

  // ---------------------------------------------------------------------------
  def dataFrame(columns: Seq[String], data: Stream[Seq[Any]]): PyDynamic =       
    pd.DataFrame(
        data    = scalaAnyssToPyAny(data), // Stream: as much as we can delay fitting it all in memory?
        columns = columns.toPythonProxy)  
        
}

// ===========================================================================
