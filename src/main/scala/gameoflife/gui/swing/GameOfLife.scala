package gameoflife.gui.swing

import gameoflife.model._

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.{BorderLayout, Dimension, Toolkit}
import java.lang.Thread.sleep
import javax.swing.{JFrame, WindowConstants}
import scala.annotation.tailrec

/**
 * A Swing UI Interface for Conway's Game of Life
 */
object GameOfLife {

  /**
   * Create a new Game of Life Grid and behold its evolution
   * @param grid A populated Grid with Dead and Live Cells
   * @param zoom Zoom factor
   */
  class GOLFrame(val grid: Grid, val zoom: Int = 1) extends JFrame("Conway's Game Of Life") {

    val canvas = new Canvas(grid, zoom)
    val dim = new Dimension(canvas.getWidth, canvas.getHeight)

    setResizable(false)
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setLayout(new BorderLayout)
    getContentPane.setPreferredSize(dim)
    add(canvas, BorderLayout.CENTER)
    pack()

    // center on screen
    val screen: Dimension = Toolkit.getDefaultToolkit.getScreenSize
    setLocation(screen.width / 2 - getSize().width / 2, screen.height / 2 - getSize().height / 2)
    setVisible(true)

    final def start(): Unit = run(grid)

    @tailrec
    final def run(grid: Grid): Unit = {
      canvas.setGrid(grid)
      val nextGrid = grid.nextGeneration()
      sleep(50)
      run(nextGrid)
    }

    this.addMouseListener(new MouseListener {
      override def mouseClicked(mouseEvent: MouseEvent): Unit = {}
      override def mousePressed(mouseEvent: MouseEvent): Unit = {}
      override def mouseReleased(mouseEvent: MouseEvent): Unit = {}
      override def mouseEntered(mouseEvent: MouseEvent): Unit = {}
      override def mouseExited(mouseEvent: MouseEvent): Unit = {}
    })
  }


  /**
   * Experiment with different patterns, offsets and grid sizes here
   */
  def main(args: Array[String]): Unit = {

    // val game = new GOLFrame( randomlyPopulatedGrid(400,300, 10), 2)

    // val coordinates = PatternLib.getPatternArray(PatternLib.against_the_grain,40,30)
    // val game = new GOLFrame( coordinatesPopulatedGrid(120,120, coordinates), 4)

    // val coordinates = PatternLib.getPatternArray(PatternLib.r_pentomino,95,80)
    // val game = new GOLFrame( coordinatesPopulatedGrid(200,150, coordinates), 3)

    // val coordinates = PatternLib.getPatternArray(PatternLib.centinal,75,65)
    // val game = new GOLFrame( coordinatesPopulatedGrid(200,150, coordinates), 3)

    // val coordinates = PatternLib.getPatternArray(PatternLib.non_monotonic,475,25)
    // val game = new GOLFrame( coordinatesPopulatedGrid(500,60, coordinates), 3)

    // watch this one crash in the left edge!
    val coordinates = PatternLib.getPatternArray(PatternLib.hammerhead,275,30)
    val game = new GOLFrame( coordinatesPopulatedGrid(300,80, coordinates), 3)

    game.start()
  }
}
