package gameoflife.patterns

import gameoflife.model.Coordinate

/**
 * A PatternLib consists of a collection of named Patterns.
 * Patterns are coded as grids of ascii characters.
 * A dead cell is a dot. A live cell an 'O' (Uppercase O).
 */
trait PatternLib {

  /**
   * Get all unique letters for which patterns exist
   * @return List of letters
   */
  def readLetters(): Seq[Char]

  /**
   * Get all patterns starting with letter
   * @param letter first letter of patterns
   * @return A map of pattern names and pattern code
   */
  def readPatterns(letter: String): Map[String,Pattern]

  /**
   * Get Pattern by its name
   * @param name Name of pattern
   * @return A Pattern
   */
  def getPattern(name: String): Pattern

  /**
   * Convert an Ascii pattern representation to an array of coordinates of live cells.
   *
   * @param pattern A String representation of the initial state of a block of cells.
   *                A dead cell is a dot. A live cell an 'O' (Uppercase O).
   *                See https://www.conwaylife.com/ref/lexicon/lex.htm for patterns in this format.
   * @param xOffset Number of cells to add from the left edge.
   * @param yOffset Number of cells to add from the top edge.
   * @return An array of coordinates containing the live cells in a pattern.
   */
  def getPatternArray(pattern: Pattern, xOffset: Int = 0, yOffset: Int = 0): Array[Coordinate] = {

    def arrayFromLine(string: String, lineNumber: Int) = {

      string.zipWithIndex
        .filter { case (char, _) => char == 'O' }
        .map { case (_, i) => Coordinate(i + xOffset, lineNumber + yOffset) }
    }

    pattern.value.split("\n").zipWithIndex
      .filter { case (line, _) => line.contains("O") }
      .flatMap { case (line, lineNumber) => arrayFromLine(line, lineNumber) }
  }

}
