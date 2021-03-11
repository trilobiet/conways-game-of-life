
import gameoflife.patterns.{HardcodedPatterns, ResourcePatterns}
import org.junit._
import org.junit.Assert.assertEquals

class GameOfLifeSuite {

  @Test def `some fancy test`: Unit =  {

    //val t = HardcodedPatterns.getPatternArray( HardcodedPatterns.getPattern("pentadecathlon"), 199, 144)
    //t.foreach(println)
/*
    val letters = HardcodedPatterns.readLetters()
    letters.foreach(println)

    val patterns = HardcodedPatterns.readPatterns("a")
    patterns.foreach(println)

    val pattern = HardcodedPatterns.getPattern("pentadecathlon")
    println(pattern)
*/
    //ResourcePatterns.getFiles().foreach(println)


    assertEquals(5, 5)
  }

}
