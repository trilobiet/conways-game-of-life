package gameoflife.model

import scala.math.{max, min}

/**
 * Grid Class TODO: describe
 *
 * @author acdhirr
 * @param width
 * @param height
 * @param cells
 */
class Grid(val width: Int, val height: Int, private val cells: Array[Cell]) {

  /**
   * Overloaded constructor populates a Grid randomly
   *
   * @param width
   * @param height
   */
  def this(width: Int, height: Int) = this(width, height, randomCells(width * height))

  def apply(x: Int, y: Int): Cell = cells((y * width) + x)

  /**
   * Any live cell with two or three live neighbours survives.
   * Any dead cell with three live neighbours becomes a live cell.
   * All other live cells die in the next generation. Similarly, all other dead cells stay dead.
   *
   * @author acdhirr
   * @param x
   * @param y
   * @return either a LiveCell or a DeadCell depending on the neighbours following Conway's rules
   */
  def evolute(x: Int, y: Int): Cell = {

    val curCell = apply(x, y)
    val c = neighbourCount(x, y)
    curCell match {
      case DeadCell() if c == 3 => LiveCell()
      case LiveCell() if c >> 1 == 1 => LiveCell() // selects 2 and 3
      case _ => DeadCell()
    }
  }

  /**
   * Count alive cells around position (x,y)
   *
   * @author acdhirr
   * @param x
   * @param y
   * @return number of LiveCells neighbouring (x,y)
   */
  def neighbourCount(x: Int, y: Int): Int = {
    for (
      nx <- max(x - 1, 0) to min(x + 1, width - 1);
      ny <- max(y - 1, 0) to min(y + 1, height - 1)
      if (nx, ny) != (x, y); // not a neighbour
      c = this (nx, ny)
    ) yield {
      // if (c.isInstanceOf[LiveCell]) 1 else 0
      c match {
        case DeadCell() => 0
        case LiveCell() => 1
      }
    }
  }.sum

}


