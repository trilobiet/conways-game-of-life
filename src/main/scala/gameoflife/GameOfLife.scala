package gameoflife

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.{BorderLayout, Dimension, Toolkit}
import java.lang.Thread.sleep
import javax.swing.{JFrame, WindowConstants}
import scala.annotation.tailrec

object GameOfLife {

  class GOLFrame(val grid: Grid, val zoom: Int = 1) extends JFrame("Conway's Game Of Life") {

    val canvas = new Canvas(grid,zoom)
    val dim = new Dimension(canvas.getWidth,canvas.getHeight)

    setResizable(false)
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setLayout(new BorderLayout)
    getContentPane().setPreferredSize(dim)
    add(canvas, BorderLayout.CENTER)
    pack()

    // center on screen
    val screen = Toolkit.getDefaultToolkit.getScreenSize
    setLocation(screen.width/2-getSize().width/2, screen.height/2-getSize().height/2)
    setVisible(true)

    final def start = run(grid)

    @tailrec
    final def run(grid: Grid): Unit = {
      canvas.setGrid(grid)
      val nextGrid = nextGeneration(grid)
      sleep(50)
      run(nextGrid)
    }

    this.addMouseListener( new MouseListener {
      override def mouseClicked(mouseEvent: MouseEvent): Unit = {}
      override def mousePressed(mouseEvent: MouseEvent): Unit = {}
      override def mouseReleased(mouseEvent: MouseEvent): Unit = {}
      override def mouseEntered(mouseEvent: MouseEvent): Unit = {}
      override def mouseExited(mouseEvent: MouseEvent): Unit = {}
    })
  }



  def main(args: Array[String]): Unit = {
    val frame = new GOLFrame(new Grid(500, 300),2)
    frame.start
  }
}
