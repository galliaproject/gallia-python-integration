package gallia

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.SeqConverters
import me.shadaj.scalapy.readwrite.Writer

// ===========================================================================
package object pandas {     
  val ScalaPyUtils = gallia.python.ScalaPyUtils
  type PyDynamic = me.shadaj.scalapy.py.Dynamic
  
  lazy val pd = py.module("pandas")

  // ---------------------------------------------------------------------------
  def isPandasNull(value: py.Any): Boolean = pd.isnull(value).as[Boolean]

  // ---------------------------------------------------------------------------  
  implicit class GalliaPandas_(data: HeadS) {
    def toPandasDataFrame: py.Dynamic =
      data
        ._forceResult /* warts and all */
        .pipe(ScalaPyPandas.dataFrame)
  }

}

// ===========================================================================
