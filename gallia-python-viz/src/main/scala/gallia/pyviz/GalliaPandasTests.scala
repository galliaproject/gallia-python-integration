package gallia
package pyviz

import gallia.pandas.ScalaPyPandas

// ===========================================================================
object GalliaPandasTests { import pandas.GalliaPandas._ 
  import pyviz._
      
  // ---------------------------------------------------------------------------
  def main(args: Array[String]): Unit = {

  
  // ---------------------------------------------------------------------------  
  if (false)
    GalliaSeaborn
      .streamBuiltinDataset(_.flights)
      .display()
else    
    SeabornExampleDatasetName
      .values
      .filterNot(_ == SeabornExampleDatasetName.brain_networks) // transposed
      .map(_.entryName)
      .foreach { name =>
        println("=" * 75)
        println(name)

        GalliaSeaborn
          .streamBuiltinDataset(name)
          .take(10)
          .display()      
      }

    println("ok")
  }

}

// ===========================================================================
