package gameoflife.patterns

import scala.collection.mutable

/**
 * Library of Game of Life Patterns in Ascii format
 * See https://www.conwaylife.com/ref/lexicon/lex.htm
 *
 * @author acdhirr
 */
object HardcodedPatterns extends PatternLib {

  var patterns: mutable.LinkedHashMap[String,Pattern] = mutable.LinkedHashMap[String,Pattern]()

  /**
   * Get all unique letters for which patterns exist
   * @return List of letters
   */
  override def readLetters(): Seq[Char] = {
    patterns.map(_._1.charAt(0)).toSet.toSeq.sorted
  }

  /**
   * Get all patterns starting with letter
   * @param letter first letter of patterns
   * @return A map of pattern names and pattern code
   */
  override def readPatterns(letter: String): Map[String, Pattern] = {
    patterns.filter(_._1.startsWith(letter)).toMap
  }

  /**
   * Get Pattern by its name
   *
   * @param name Name of pattern
   * @return A Pattern
   */
  override def getPattern(name: String): Pattern = patterns(name)


  /*
   *  The Library
   *
   */

  patterns += ("blinker" ->  Pattern(
    """
      |OOO
      |""".stripMargin))

  patterns += ("toad" -> Pattern(
    """
      |.OOO
      |OOO.
      |""".stripMargin))

  patterns += ("acorn" -> Pattern(
    """
      |.O.....
      |...O...
      |OO..OOO
      |""".stripMargin))

  patterns += ("pentadecathlon" ->  Pattern(
    """
      |..O....O..
      |OO.OOOO.OO
      |..O....O..
      |""".stripMargin))

  patterns += ("gospers_glider_gun" ->  Pattern(
    """
      |........................O...........
      |......................O.O...........
      |............OO......OO............OO
      |...........O...O....OO............OO
      |OO........O.....O...OO..............
      |OO........O...O.OO....O.O...........
      |..........O.....O.......O...........
      |...........O...O....................
      |............OO......................
      |""".stripMargin))

  patterns += ("p54_shuttle" ->  Pattern(
    """
      |OO.........................OO
      |.O.........................O.
      |.O.O.......O.............O.O.
      |..OO.....O..O.....O......OO..
      |............O.....OO.........
      |........O..........OO........
      |........O...OO....OO.........
      |.........OOOOO...............
      |.............................
      |.........OOOOO...............
      |........O...OO....OO.........
      |........O..........OO........
      |............O.....OO.........
      |..OO.....O..O.....O......OO..
      |.O.O.......O.............O.O.
      |.O.........................O.
      |OO.........................OO
      |""".stripMargin))

    patterns += ("koks_galaxy" ->  Pattern(
    """
      |OOOOOO.OO
      |OOOOOO.OO
      |.......OO
      |OO.....OO
      |OO.....OO
      |OO.....OO
      |OO.......
      |OO.OOOOOO
      |OO.OOOOOO
      |""".stripMargin
  ))

  patterns += ("against_the_grain" ->  Pattern(
    """
      |...O..O..O..O..O..O..O..O..O..O..O...
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |O...................................O
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |.....................................
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |O...................................O
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |.....................................
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |O...................................O
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |.....................................
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |O...................................O
      |.OOOOOOOOOOOOOOOOO..OOOOOOOOOOOOOOOO.
      |.....................................
      |.OOOOOOOOOOOOOOO......OOOOOOOOOOOOOO.
      |O...............O....O..............O
      |.OOOOOOOOOOOOOOOO....OOOOOOOOOOOOOOO.
      |.....................................
      |.OOOOOOOOOOOOO...OOOO...OOOOOOOOOOOO.
      |O.................OO................O
      |.OOOOOOOOOOOO............OOOOOOOOOOO.
      |.............O..........O............
      |.OOOOOOOOOOOOOO........OOOOOOOOOOOOO.
      |O..............O......O.............O
      |.OOOOOOOOOOOOOOO......OOOOOOOOOOOOOO.
      |..........OO....O....O....OO.........
      |.OOOOOOO......OOOO..OOOO......OOOOOO.
      |O.......O...OO...O..O...OO...O......O
      |.OOOOOOO.........O..O.........OOOOOO.
      |.........O.....O......O.....O........
      |.OOOOOOOOO......O....O......OOOOOOOO.
      |O.........O....OO.OO.OO....O........O
      |.OOOOOOOOOOO....O....O....OOOOOOOOOO.
      |............OO....OO....OO...........
      |.OOOOOOO..OOO.O..O..O..O.OOO..OOOOOO.
      |O..............OOO..OOO.............O
      |.OOOOO......OOO.O....O.OOO......OOOO.
      |......O....O..............O....O.....
      |.OOOOOO........O......O........OOOOO.
      |O......O...OO..O..OO..O..OO...O.....O
      |.OOOOOOOO.....O.OO..OO.O.....OOOOOOO.
      |.........O..O.OO......OO.O..O........
      |.OOOOOOOOO...OO........OO...OOOOOOOO.
      |O..........O..............O.........O
      |.OOOOOOOOOOOOOOOO....OOOOOOOOOOOOOOO.
      |.................OOOO................
      |.OOOOOOOOOOOOOOOOO..OOOOOOOOOOOOOOOO.
      |O...................................O
      |.OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.
      |...O..O..O..O..O..O..O..O..O..O..O...
      |""".stripMargin
  ))

  patterns += ("r_pentomino" ->  Pattern(
    """
      |.OO
      |OO.
      |.O.
      |""".stripMargin))

  patterns += ("centinal" ->  Pattern(
    """
      |OO................................................OO
      |.O................................................O.
      |.O.O.....................OO.....................O.O.
      |..OO........O............OO............OO.......OO..
      |...........OO..........................O.O..........
      |..........OO.............................O..........
      |...........OO..OO......................OOO..........
      |....................................................
      |....................................................
      |....................................................
      |...........OO..OO......................OOO..........
      |..........OO.............................O..........
      |...........OO..........................O.O..........
      |..OO........O............OO............OO.......OO..
      |.O.O.....................OO.....................O.O.
      |.O................................................O.
      |OO................................................OO
      |""".stripMargin))

  patterns += ("non_monotonic" ->  Pattern(
    """
      |..........OO.O.......
      |......OOO.O.OOO......
      |..O.O..........O...OO
      |OO....OO.....O...OOOO
      |..O.OO..O....OOO.O...
      |........O....O.......
      |..O.OO..O....OOO.O...
      |OO....OO.....O...OOOO
      |..O.O..........O...OO
      |......OOO.O.OOO......
      |..........OO.O.......
      |""".stripMargin))

  patterns += ("x66" -> Pattern(
    """
      |..O......
      |OO.......
      |O..OOO..O
      |O....OOO.
      |.OOO..OO.
      |.........
      |.OOO..OO.
      |O....OOO.
      |O..OOO..O
      |OO.......
      |..O......
      |""".stripMargin))

  patterns += ("candelabra" ->  Pattern(
    """
      |....OO....OO....
      |.O..O......O..O.
      |O.O.O......O.O.O
      |.O..O.OOOO.O..O.
      |....O.O..O.O....
      |.....O....O.....
      |""".stripMargin))

  patterns += ("hammerhead" ->  Pattern(
    """
      |................O..
      |.OO...........O...O
      |OO.OOO.......O.....
      |.OOOOO.......O....O
      |..OOOOO.....O.OOOO.
      |......OOO.O.OO.....
      |......OOO....O.....
      |......OOO.OOO......
      |..........OO.......
      |..........OO.......
      |......OOO.OOO......
      |......OOO....O.....
      |......OOO.O.OO.....
      |..OOOOO.....O.OOOO.
      |.OOOOO.......O....O
      |OO.OOO.......O.....
      |.OO...........O...O
      |................O..
      |""".stripMargin))

  patterns += ("hectic" -> Pattern(
    """
      |......................OO...............
      |......................OO...............
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.........O..........OO...OO............
      |.......O.O............OOO..............
      |......O.O............O...O.............
      |OO...O..O.............O.O..............
      |OO....O.O..............O...............
      |.......O.O......O.O....................
      |.........O......OO.....................
      |.................O...O.................
      |.....................OO......O.........
      |....................O.O......O.O.......
      |...............O..............O.O....OO
      |..............O.O.............O..O...OO
      |.............O...O............O.O......
      |..............OOO............O.O.......
      |............OO...OO..........O.........
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |.......................................
      |...............OO......................
      |...............OO......................
      |""".stripMargin))

  patterns += ("heisenburp_device" ->  Pattern(
    """
      |O.....O....
      |.OO...O.O..
      |OO....OO...
      |...........
      |...........
      |...........
      |.........OO
      |........O.O
      |..........O
      |""".stripMargin))

  patterns += ("stamp_collection" ->  Pattern(
    """
      |.O......O.O.....O....O.O.O...................O.
      |............................................O..
      |.O.....O...O...O.O...O......................OOO
      |...............................................
      |.O.....O...O..O...O..O.O.O.....................
      |...............................................
      |.O.....O...O..O.O.O..O.........................
      |........................................OO.....
      |.O.O.O..O.O...O...O..O.................O.O.....
      |.........................................O.....
      |...............................................
      |...............................................
      |.............................................O.
      |............................................O..
      |O.O.O....O....O.O.O..O.O.O..O.O.............OOO
      |................................O..............
      |O.......O.O.....O....O......O..................
      |................................O..............
      |O.O.O..O...O....O....O.O.O..O.O................
      |...............................................
      |O......O.O.O....O....O......O..O...........O...
      |..........................................OO...
      |O.O.O..O...O....O....O.O.O..O...O.........O.O..
      |""".stripMargin))


  patterns += ("acdhirr1" ->  Pattern(
    """
      |OOOOOOOOO..OOOOOOOOO
      |OOOOOOOOO..OOOOOOOOO
      |OO.....OO..OO.....OO
      |OO..O..OO..OO..O..OO
      |OO.OOO.OO..OO.OOO.OO
      |OO..O..OO..OO..O..OO
      |OO.....OO..OO.....OO
      |OOOOOOOOO..OOOOOOOOO
      |OOOOOOOOO..OOOOOOOOO
      |""".stripMargin))

  patterns += ("acdhirr2" -> Pattern(
    """
      |OOOOOOOOOOO
      |...........
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |...........
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |...........
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |...........
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |...........
      |OOOOOOOOOOO
      |OOOOOOOOOOO
      |...........
      |OOOOOOOOOOO
      |""".stripMargin))

  // Text to ascii: http://patorjk.com/software/taag/#p=display&h=0&w=.&f=Banner3&t=ACDHIRR

  patterns += ("text_acdhirr" ->  Pattern(
    """
      |...OOO.....OOOOOO..OOOOOOOO..OO.....OO.OOOO.OOOOOOOO..OOOOOOOO.
      |..OO.OO...OO....OO.OO.....OO.OO.....OO..OO..OO.....OO.OO.....OO
      |.OO...OO..OO.......OO.....OO.OO.....OO..OO..OO.....OO.OO.....OO
      |OO.....OO.OO.......OO.....OO.OOOOOOOOO..OO..OOOOOOOO..OOOOOOOO.
      |OOOOOOOOO.OO.......OO.....OO.OO.....OO..OO..OO...OO...OO...OO..
      |OO.....OO.OO....OO.OO.....OO.OO.....OO..OO..OO....OO..OO....OO.
      |OO.....OO..OOOOOO..OOOOOOOO..OO.....OO.OOOO.OO.....OO.OO.....OO
      |""".stripMargin))

  patterns += ("text_max" ->  Pattern(
    """
      |OO.....OO....OOO....OO.....OO
      |OOO...OOO...OO.OO....OO...OO.
      |OOOO.OOOO..OO...OO....OO.OO..
      |OO.OOO.OO.OO.....OO....OOO...
      |OO.....OO.OOOOOOOOO...OO.OO..
      |OO.....OO.OO.....OO..OO...OO.
      |OO.....OO.OO.....OO.OO.....OO
      |""".stripMargin))

  patterns += ("text_god" ->  Pattern(
    """
      |.OOOOOO....OOOOOOO..OOOOOOOO.
      |OO....OO..OO.....OO.OO.....OO
      |OO........OO.....OO.OO.....OO
      |OO...OOOO.OO.....OO.OO.....OO
      |OO....OO..OO.....OO.OO.....OO
      |OO....OO..OO.....OO.OO.....OO
      |.OOOOOO....OOOOOOO..OOOOOOOO.
      |""".stripMargin))

  patterns += ("text_time" ->  Pattern(
    """
      |OOO.O.OO.OO.OOOO
      |.O..O.O.O.O.O...
      |.O..O.O...O.OOOO
      |.O..O.O...O.O...
      |.O..O.O...O.OOOO
      |""".stripMargin
  ))

}
