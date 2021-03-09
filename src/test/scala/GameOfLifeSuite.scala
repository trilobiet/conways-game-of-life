
import gameoflife.model.PatternLib
import org.junit._
import org.junit.Assert.assertEquals
import PatternLib._

class GameOfLifeSuite {

  @Test def `weight of a larger tree (10pts)`: Unit =  {

    val t = PatternLib.getPatternArray( pentadecathlon, 199, 144)
    t.foreach(println)

    assertEquals(5, 5)
  }

}
