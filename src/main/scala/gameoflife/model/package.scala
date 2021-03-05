package gameoflife

package object model {

  /**
   * The Cells
   */
  sealed abstract class Cell

  case class DeadCell() extends Cell

  case class LiveCell() extends Cell

  /**
   * Randomly populate an Array with Cells
   *
   * @author acdhirr
   * @param size
   * @param sparseness
   * @return An array of size 'size' with one in 'sparseness' cells randomly populated
   */
  def randomCells(size: Int, sparseness: Int = 10) = {
    val r = scala.util.Random
    val cells = new Array[Cell](size)
    val area = cells.length / 6 to cells.length - cells.length / 6
    for (i <- 0 until cells.length) {
      val p = r.nextInt(sparseness)
      cells(i) = if (p < 1 && area.contains(i)) LiveCell() else DeadCell()
    }
    cells
  }

  /**
   * Evolute a Grid to the next generation
   *
   * TODO move inside Grid
   *
   * @author acdhirr
   * @param parent
   * @return The Grid of Cells that descends from Grid 'parent' following Conway's rules
   */
  def nextGeneration(parent: Grid): Grid = {

    val cells = for (
      y <- (0 until parent.height);
      x <- (0 until parent.width)
    ) yield {
      parent.evolute(x, y)
    }
    new Grid(parent.width, parent.height, cells.toArray)
  }

}
