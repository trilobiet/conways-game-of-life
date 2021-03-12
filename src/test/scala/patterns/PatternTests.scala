package patterns

import gameoflife.{model, patterns}
import gameoflife.patterns.{HardcodedPatterns, Pattern, ResourcePatterns}
import org.junit.Assert.assertEquals
import org.junit.Test

class PatternTests {

  @Test def test_BalancedPatterns: Unit =  {

    val pl = new ResourcePatterns("/test-library.txt")
    val toad = pl.getPattern("toad")
    assert(toad.isBalanced,"patterns must be balanced")
  }

  @Test def test_UnbalancedPatterns: Unit =  {

    val success: Boolean = try {
      val unBalancedPattern = Pattern(
        """O..O
          |O...O
          |""".stripMargin.trim)
      true
    }
    catch {
      case e:Exception => false
    }

    assert( !success, "Unbalanced patterns must throw an Exception" )
  }


  @Test def test_ResourcePatterns: Unit =  {

    val pl = new ResourcePatterns("/test-library.txt")

    assertEquals("test library unique letters should be 3", 3, pl.getLetters().length)
    assertEquals("test library patterns with letter 'a' should be 2", 2, pl.getPatterns("a").size)
    assertEquals("test library pattern 'toad' should be 2 lines", 2, pl.getPattern("toad").value.lines().count())
  }

  @Test def test_HardCodedPatterns: Unit =  {

    val pl = HardcodedPatterns

    assert(pl.getLetters().length > 10, "hard coded library unique letters should be 10 or more")
    assert(pl.getPatterns("a").size > 0, "hard coded library should have at least one pattern with letter 'a'")
    assertEquals("hard coded pattern 'toad' should be 2 lines", 2, pl.getPattern("toad").value.trim.lines().count())
  }

  @Test def test_Live_Cells_from_Patterns: Unit =  {

    val toad: patterns.Pattern = HardcodedPatterns.getPattern("toad")
    val coords: Array[model.Coordinate] = HardcodedPatterns.getLiveCellsFromPattern(toad)
    assertEquals("hard coded pattern 'toad' should have 6 Live Cells", 6, coords.length)
  }

  /*
  @Test def test_Whatever: Unit =  {

      val pl = new ResourcePatterns("/library.txt")
      pl.getPatterns().foreach(p => println(p._1))
  }
  */


}
