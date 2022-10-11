package gallia
package pyviz

import aptus._
import me.shadaj.scalapy.py
      
// ===========================================================================
private[gallia] object GalliaSeaborn {          
  lazy val sns = py.module("seaborn")
  lazy val plt = GalliaMatplotlib.plt  
  import gallia.pandas._
  import GalliaPandas._
  
  // --------------------------------------------------------------------------- 
  def streamBuiltinDataset(nameChooser: SeabornExampleDatasetName.type => SeabornExampleDatasetName): HeadZ =
    streamBuiltinDataset(nameChooser(SeabornExampleDatasetName).entryName)

  // ---------------------------------------------------------------------------
  def streamBuiltinDataset(name: String): HeadZ =    
    GalliaSeaborn
      .sns
      .load_dataset(name)
      .pipe(pandasDataFrameToHeadS)

      
  // ===========================================================================
  def lineplot(z: HeadZ)(x: KeyW, y: KeyW): PyDynamic =    
    sns.lineplot(
        data = z.retain(x.value, y.value).toPandasDataFrame,
        x    = x.skey,
        y    = y.skey)

}

// ===========================================================================
