package gallia
package pyviz

import aptus._

// ===========================================================================
object GalliaVizTest {
  lazy val Test1 = bobjs(bobj(_vle -> 1), bobj(_vle -> 2), bobj(_vle -> 3), bobj(_vle -> 2)).identity

  lazy val Flights = "/data/seaborn/flights.csv".stream()
    
  // ===========================================================================
  def main(args: Array[String]): Unit = {
  if (false)
    Test1.viz.matplotlib.histogram(_vle, bins = 3).show()

    // ---------------------------------------------------------------------------
    import pyviz._
    
    if (false) {
        GalliaMatplotlib.plt.hist(Test1.pyarray(_vle), bins = 3, log = true)
        GalliaMatplotlib.plt.show() }
    
    if (false)
        plt
          .custom { _.hist(x = Test1.pyarray(_vle), bins = 3, log = true) }
          .show()
          
    if (false)          
      Test1.viz.matplotlib.histogram(_vle, bins = 3).show()
      
    if (false)  
      Test1.hist(_vle, bins = 3)
      
    // ---------------------------------------------------------------------------
    //if (false)
      GalliaSeaborn
        .streamBuiltinDataset(_.flights)
        .filterBy("month").hasValue("May")    
        .lineplot(x = "year", y = "passengers")
      
  }

}

// ===========================================================================
