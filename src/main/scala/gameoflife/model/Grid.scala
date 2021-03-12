package gameoflife.model

import scala.math.{max, min}

/**
 * Grid Class
 *
 * @author acdhirr
 * @param width Width of grid in cells
 * @param height Height of grid in cells
 * @param cells Array of Cells (DeadCell and LiveCell)
 */
class Grid(val width: Int, val height: Int, val cells: Array[Cell]) {

  /**
   * Get Cell at position (x,y)
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return Cell at position (x,y)
   */
  def apply(x: Int, y: Int): Cell = cells((y * width) + x)

  /**
   * Set cell at (x,y) alive when dead and vice versa
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return A Grid with the modified Cell
   */
  def toggle(x: Int, y: Int): Grid = {

    cells((y * width) + x) = this(x,y) match {
      case DeadCell() => LiveCell()
      case LiveCell() => DeadCell()
    }
    new Grid(width, height, cells)
  }

  /**
   * Clear grid
   * @return A Grid with only DeadCells
   */
  def clear(): Grid = {
    for (i <- cells.indices) cells(i) = DeadCell()
    new Grid(width, height, cells)
  }

  /**
   * This is Conway's Algorithm:
   * Any live cell with two or three live neighbours survives.
   * Any dead cell with three live neighbours becomes a live cell.
   * All other live cells die in the next generation. Similarly, all other dead cells stay dead.
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return either a LiveCell or a DeadCell depending on the neighbours following Conway's rules
   */
  def evolute(x: Int, y: Int): Cell = {

    val curCell = apply(x, y)
    val c = neighbourCount(x, y)
    curCell match {
      case DeadCell() if c == 3 => LiveCell()
      case LiveCell() if c >> 1 == 1 => LiveCell() // binary shift selects 2 and 3
      case _ => DeadCell()
    }
  }

  /**
   * Count live cells neighbouring position (x,y)
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return number of LiveCells neighbouring (x,y)
   */
  def neighbourCount(x: Int, y: Int): Int = {
    for (
      nx <- max(x - 1, 0) to min(x + 1, width - 1);
      ny <- max(y - 1, 0) to min(y + 1, height - 1)
      if (nx, ny) != (x, y); // not a neighbour
      c = this (nx, ny)
    ) yield {
      c match {
        case DeadCell() => 0
        case LiveCell() => 1
      }
    }
  }.sum


  /**
   * Evolute this Grid to the next generation
   *
   * @return The Grid of Cells that descends from the current Grid following Conway's rules
   */
  def nextGeneration(): Grid = {

    val cells = for (
      y <- 0 until height;
      x <- 0 until width
    ) yield {
      evolute(x, y)
    }
    new Grid(width, height, cells.toArray)
  }



}


