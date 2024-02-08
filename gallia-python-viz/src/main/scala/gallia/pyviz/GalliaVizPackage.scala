package gallia

import me.shadaj.scalapy.py

// ===========================================================================
package object pyviz {  
  val ScalaPyUtils = gallia.python.ScalaPyUtils
  type PyDynamic = me.shadaj.scalapy.py.Dynamic
  
lazy val plt = GalliaMatplotlib.plt
lazy val sns = GalliaSeaborn   .sns

import pandas.pd
def isPandasNull(value: py.Any): Boolean = pd.isnull(value).as[Boolean]

  // ===========================================================================
  implicit class Module_(mod: py.Module) {
    /** eg:
          plt
            .custom { _.hist(x = Test1.pythonValues(_vle), bins = 3, log = true) }
            .show() */
    def custom(f: py.Module => Unit): MatplotlibBased =
      new MatplotlibBased { 
        def common() = _ => f(mod) }             
  }
  
  // ===========================================================================
  implicit class HeadSViz__(z: HeadS) {
      def pyarray(path: KPathW) = z.forceAnys(path).pipe(ScalaPyUtils.scalaAnysToPyAny)
    
      def viz = new Viz(z)

      // ---------------------------------------------------------------------------
      // shorthands
      def hist(x: KeyW, bins: Int) = viz.matplotlib.histogram(x.value, bins).show() // TODO: on first
        //TODO: bins optional
      
      def lineplot(x: KeyW, y: KeyW): Unit = { viz.seaborn.lineplot(x, y).show() } // TODO: version where takes first two
    }
  
    // ===========================================================================
    class Viz private[pyviz] (z: HeadS) {
        def matplotlib = new Matplotlib(z)
        def seaborn    = new Seaborn   (z)
      }
      
      // ===========================================================================
      class Matplotlib private[pyviz] (z: HeadS) {        
        def histogram(path: KPathW, bins: Int) =
          GalliaMatplotlib.histogram(z)(path, bins)
//          new MatplotlibBased { 
//          def common() = _ => GalliaMatplotlib.histogram(z)(path, bins) }

        def histogram2(path: KPathW, bins: Int) = new MatplotlibBased { 
          def common() = _ => plt.custom { _.hist(
              x    = z.pyarray(path),
              bins = bins) }
          //.show()          
        }        

      }

      // ---------------------------------------------------------------------------
      class Seaborn private[pyviz] (z: HeadS) {    
        def lineplot(x: KeyW, y: KeyW) = new MatplotlibBased {
          def common() = _ => GalliaSeaborn.lineplot(z)(x, y) } }

    // ---------------------------------------------------------------------------
    /*sealed */trait MatplotlibBased { //import GalliaMatplotlib.plt
      protected def common(): Unit => Unit

      def show   ()            : Unit = { common(); plt.show() }
      def savefig(path: String): Unit = { common(); plt.savefig(path) }          
    }              
}

// ===========================================================================
