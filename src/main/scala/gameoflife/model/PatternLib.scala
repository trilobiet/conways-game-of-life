package gameoflife.model

/**
 * Library of Game of Life Patterns in Ascii format
 * See https://www.conwaylife.com/ref/lexicon/lex.htm
 */
object PatternLib {

  case class Pattern(value: String) extends AnyVal

  /**
   *
   * @param pattern A String representation of the initial state of a block of cells.
   *                A dead cell is a dot. A live cell an 'O' (Uppercase O).
   *                See https://www.conwaylife.com/ref/lexicon/lex.htm for patterns in this format.
   * @param xOffset Number of cells to add from the left edge.
   * @param yOffset Number of cells to add from the top edge.
   * @return An array of coordinates containing the live cells in a pattern.
   */
  def getPatternArray(pattern: Pattern, xOffset: Int =0, yOffset: Int = 0 ): Array[Coordinate] = {

    def arrayFromLine(string: String, lineNumber: Int) = {

      string.zipWithIndex
        .filter{ case(char,_) => char == 'O' }
        .map{ case(_,i) => Coordinate(i + xOffset,lineNumber + yOffset)}
    }

    pattern.value.split("\n").zipWithIndex
      .filter { case(line,_) => line.contains("O") }
      .flatMap { case(line,lineNumber) => arrayFromLine(line,lineNumber) }
  }

  /*
   *  The Library
   *
   */

  val blinker: Pattern = Pattern("""
    |OOO
    |""".stripMargin)

  val toad: Pattern = Pattern("""
    |.OOO
    |OOO.
    |""".stripMargin)

  val acorn: Pattern = Pattern("""
    |.O.....
    |...O...
    |OO..OOO
    |""".stripMargin)

  val pentadecathlon: Pattern = Pattern("""
    |..O....O..
    |OO.OOOO.OO
    |..O....O..
    |""".stripMargin)

  val gospers_glider_gun: Pattern = Pattern("""
    |........................O...........
    |......................O.O...........
    |............OO......OO............OO
    |...........O...O....OO............OO
    |OO........O.....O...OO..............
    |OO........O...O.OO....O.O...........
    |..........O.....O.......O...........
    |...........O...O....................
    |............OO......................
    |""".stripMargin)

  val p54_shuttle: Pattern = Pattern(
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
      |""".stripMargin)

  val koks_galaxy: Pattern = Pattern("""
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
  )

  val against_the_grain: Pattern = Pattern(
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
  )

  val r_pentomino: Pattern = Pattern("""
      |.OO
      |OO.
      |.O.
      |""".stripMargin)

  val centinal: Pattern = Pattern("""
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
      |""".stripMargin)

  val non_monotonic: Pattern = Pattern("""
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
      |""".stripMargin)

  val x66: Pattern = Pattern("""
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
      |""".stripMargin)

  val candelabra: Pattern = Pattern("""
      |....OO....OO....
      |.O..O......O..O.
      |O.O.O......O.O.O
      |.O..O.OOOO.O..O.
      |....O.O..O.O....
      |.....O....O.....
      |""".stripMargin)

  val hammerhead: Pattern = Pattern("""
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
      |""".stripMargin)

}
