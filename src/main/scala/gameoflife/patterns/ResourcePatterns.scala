package gameoflife.patterns


import scala.collection.immutable.ListMap
import scala.io.Source

/**
 * PatternLib implementation that gets its patterns from the resources
 * on the classpath (included in .jar file).
 *
 * Patterns are stored in directory 'patterns' and then in
 * subdirectories named by their first letter.
 */
object ResourcePatterns extends PatternLib {

  // Init patterns
  private val patterns = {
    val m: Map[String, Pattern] = readLibrary()
    ListMap(m.toSeq.sortBy(_._1):_*) // sort by key
  }

  /**
   * Get all letters for which patterns exist
   *
   * @return List of letters
   */
  override def readLetters(): Seq[Char] = {
    patterns.map(_._1.charAt(0)).toSet.toSeq.sorted
  }

  /**
   * Get all patterns starting with letter
   *
   * @param letter First letter of requested patterns
   * @return A map of pattern names and patterns
   */
  override def readPatterns(letter: String): Map[String, Pattern] = {
    patterns.filter(_._1.startsWith(letter))
  }

  /**
   * Get Pattern by its name
   *
   * @param name Name of pattern
   * @return A Pattern
   */
  override def getPattern(name: String): Pattern =
    patterns(name)


  /*
   * Read patterns from file library.txt on classpath
   * @return A Map (name -> pattern)
   */
  private def readLibrary() = {
    val fileStream = getClass.getResourceAsStream("/library.txt")
    val lines: Iterator[String] = Source.fromInputStream(fileStream).getLines
    val patternDefs: Array[String] = lines.mkString("\n").split("\n\n")
    val mappings = patternDefs
      .map(parseSinglePattern).filter(_.nonEmpty).map(_.get).toMap
    mappings
  }

  /*
   * @param blob A String of which the first line is the pattern name and subsequent lines the pattern
   * @return A tuple (name, pattern)
   */
  private def parseSinglePattern( blob:String ) = {
    val split: Array[String] = blob.split("\n",2)
    if (split.length == 2) Option( split(0) -> Pattern(split(1)) )
    else None
  }

}
