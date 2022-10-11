package gallia
package pandas

import enumeratum.{Enum, EnumEntry}
import aptus._
import gallia.reflect.BasicType
import me.shadaj.scalapy.readwrite.Reader

// ===========================================================================
object PandasSchema { 

  case class Cls2(fields: Seq[Fld2]) {      
    def cls = fields.map(_.fld).pipe(Cls.apply)

    // ---------------------------------------------------------------------------
    /** for efficient retrieval */
    def readers: Vector[Reader[_]] =
      fields
        .map(_.dataType.reader)
        .toVector

    // ---------------------------------------------------------------------------    
    override def toString: String = formatDefault
      def formatDefault: String =
        fields.map(_.formatDefault).section
  }
  
  // ===========================================================================
  case class Fld2(name: SKey, dataType: DataType2) {
    override def toString: String = formatDefault
      def formatDefault: String = 
        s"${name}\t${dataType}"
        
// https://stackoverflow.com/questions/38372016/split-nested-array-values-from-pandas-dataframe-cell-over-multiple-rows        
    def fld = Fld.opt(name.symbol, dataType.basicType)
  }
  
  // ===========================================================================
  sealed trait DataType2 extends EnumEntry {
      def reader   : Reader[_]
      def basicType: BasicType
    }
  
    // ---------------------------------------------------------------------------
    object DataType2 extends Enum[DataType2] {
      val values = findValues
  
      // ---------------------------------------------------------------------------
      // see https://pandas.pydata.org/docs/user_guide/basics.html#dtypes
      case object bool     extends DataType2 { val reader = Reader.booleanReader; val basicType = BasicType._Boolean }
      case object int64    extends DataType2 { val reader = Reader.intReader    ; val basicType = BasicType._Int }
      case object float64  extends DataType2 { val reader = Reader.doubleReader ; val basicType = BasicType._Double }
      
      case object string   extends DataType2 { val reader = Reader.stringReader ; val basicType = BasicType._String }      
      case object category extends DataType2 { val reader = Reader.stringReader ; val basicType = BasicType._String } // TODO: enum - t220331174826

//Exception in thread "main" java.util.NoSuchElementException: object is not a member of Enum (bool, int64, float64, string, category)      
case object `object` extends DataType2 { val reader = Reader.stringReader ; val basicType = BasicType._String }

      //TODO: object, datetime64 (eg pd.Timestamp('20180310')) and timedelta
    }
  
}

// ===========================================================================
