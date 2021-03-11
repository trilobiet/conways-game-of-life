package gameoflife

/**
 * Model package
 * @author acdhirr
 */
package object model {

  case class Coordinate(x:Int, y:Int)

  /**
   * The Cells
   */
  sealed abstract class Cell
  case class DeadCell() extends Cell
  case class LiveCell() extends Cell

  /**
   * Get a randomly populated Grid
   *
   * @author acdhirr
   * @param width width in cells
   * @param height height in cells
   * @param sparseness fraction of live cells
   * @return A randomly populated grid
   */
  def soupPopulatedGrid(width:Int, height: Int, sparseness: Int = 10 ): Grid = {

    val r = scala.util.Random
    val cells = new Array[Cell](width * height)
    val area = cells.length / 6 to cells.length - cells.length / 6
    for (i <- cells.indices) {
      val p = r.nextInt(sparseness)
      cells(i) = if (p < 1 && area.contains(i)) LiveCell() else DeadCell()
    }
    new Grid(width, height, cells)
  }

  /**
   * Get a grid populated with live cells as provided by the 'coordinates' array.
   * Remaining cells will be dead.
   *
   * @author acdhirr
   * @param width width in cells
   * @param height height in cells
   * @param coordinates array with coordinates of live cells
   * @return A pre-populated grid
   */
  def coordinatesPopulatedGrid( width:Int, height: Int, coordinates: Array[Coordinate] ): Grid = {

    val cells: Seq[Cell] = for (
      y <- 0 until height;
      x <- 0 until width
    ) yield {
      if (coordinates.contains(Coordinate(x,y))) LiveCell()
      else DeadCell()
    }

    new Grid(width, height, cells.toArray)
  }

}
