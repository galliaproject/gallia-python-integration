package gallia
package pyviz

import aptus._
import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.Module
      
// ===========================================================================
private object GalliaMatplotlib {        
  lazy val plt = py.module("matplotlib.pyplot")
    
  // ---------------------------------------------------------------------------
  def histogram(z: HeadZ)(path: KPathW, bins: Int)/*: Unit*/ =
    plt.custom { _.hist(
      x    = z.pyarray(path),
      bins = bins) }

  def histogram0(z: HeadZ)(path: KPathW, bins: Int): Unit =
    plt.hist( //https://matplotlib.org/3.5.0/api/_as_gen/matplotlib.pyplot.hist.html
        z.pyarray(path), // TODO: optimize (see 220331141313), since only one type, no need to pattern match on each value
        bins)  
//.forceAnys(path).pipe(ScalaPyUtils.scalaAnysToPyAny)        

        
  // ---------------------------------------------------------------------------
        type Data = py.Any
        type Bins = Int
  def histogram2(z: HeadZ)(path: KPathW, params: (String, Any)*): Unit = {
    
  }
//val data = z.forceAnys(path).pipe(ScalaPyUtils.scalaAnysToPyAny)
//
//
////println(py.Dynamic.global.`type`(xx)) <class 'function'>
//// applyDynamic does not support passing a vararg parameter
//    plt.hist(
//        data,
//        bins)  
//  }
        
  def custom(z: HeadZ)(f: Module => Unit): Unit = {
    
  }
}

// ===========================================================================
