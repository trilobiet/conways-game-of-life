package patterns

import gameoflife.{model, patterns}
import gameoflife.patterns.{HardcodedPatterns, ResourcePatterns}
import org.junit.Assert.assertEquals
import org.junit.Test

class PatternTests {

  @Test def `test ResourcePatterns`: Unit =  {

    val pl = new ResourcePatterns("/test-library.txt")

    assertEquals("test library unique letters should be 3", 3, pl.getLetters().length)
    assertEquals("test library patterns with letter 'a' should be 2", 2, pl.getPatterns("a").size)
    assertEquals("test library pattern 'toad' should be 2 lines", 2, pl.getPattern("toad").value.lines().count())
  }

  @Test def `test HardCodedPatterns`: Unit =  {

    val pl = HardcodedPatterns

    assert(pl.getLetters().length > 10, "hard coded library unique letters should be 10 or more")
    assert(pl.getPatterns("a").size > 0, "hard coded library should have at least one pattern with letter 'a'")
    assertEquals("hard coded pattern 'toad' should be 2 lines", 2, pl.getPattern("toad").value.trim.lines().count())
  }

  @Test def `test Live Cells from Patterns`: Unit =  {

    val toad: patterns.Pattern = HardcodedPatterns.getPattern("toad")
    val coords: Array[model.Coordinate] = HardcodedPatterns.getLiveCellsFromPattern(toad)
    assertEquals("hard coded pattern 'toad' should have 6 Live Cells", 6, coords.length)
  }

}
