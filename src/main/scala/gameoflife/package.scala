import scala.math.{max,min}
import scala.collection.parallel.CollectionConverters._

package object gameoflife {

  /**
   * The Cells
   */
  abstract class Cell
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
    val area = cells.length/6 to cells.length - cells.length/6
    for (i <- 0 until cells.length) {
      val p = r.nextInt(sparseness)
      cells(i) = if (p < 1 && area.contains(i)) LiveCell() else DeadCell()
    }
    cells
  }

  /**
   * Evolute a Grid to the next generation
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
      parent.evolute(x,y)
    }
    new Grid(parent.width,parent.height,cells.toArray)
  }


  /**
   * Grid Class TODO: describe
   *
   * @author acdhirr
   * @param width
   * @param height
   * @param cells
   */
  class Grid(val width:Int, val height:Int, private val cells: Array[Cell] ) {

    /**
     * Overloaded constructor populates a Grid randomly
     * @param width
     * @param height
     */
    def this(width: Int, height: Int) = this(width, height, randomCells(width * height))

    def apply(x: Int, y: Int) = cells((y * width) + x)

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
    def evolute(x:Int, y:Int): Cell = {

      val curCell = apply(x,y)
      val c = neighbourCount(x,y)
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
    def neighbourCount(x:Int, y:Int): Int = {
      for (
        nx <- max(x-1,0) to min(x+1,width-1);
        ny <- max(y-1,0) to min(y+1,height-1);
        if (nx,ny) != (x,y); // not a neighbour
        c = this(nx,ny)
      ) yield
        if (c.isInstanceOf[LiveCell]) 1 else 0
    }.sum

  }

}
