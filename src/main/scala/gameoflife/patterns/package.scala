package gameoflife

package object patterns {

  /**
   * Wrapper class for a pattern String
   *
   * @param value Ascii representation of start pattern for a Game Of Life evolution
   */
  case class Pattern(value: String) {

    private val chars = value.replaceAll("[\\n\\t ]", "")
    val height: Int = value.lines().count().intValue()
    val width: Int = chars.length/height
    val isBalanced = chars.length % height == 0
    require(isBalanced)
  }

}
