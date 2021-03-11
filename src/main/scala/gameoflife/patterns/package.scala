package gameoflife

package object patterns {

  /**
   * Wrapper class for a pattern String
   *
   * @param value Ascii representation of start pattern for a Game Of Life evolution
   */
  case class Pattern(value: String) extends AnyVal

}
