package gameoflife.gui.swing

import gameoflife.model.{Grid, LiveCell}

import java.awt.event.{MouseAdapter, MouseEvent}
import java.awt.image.BufferedImage
import java.awt.{Color, Graphics}
import javax.swing.JComponent
import scala.collection.parallel.CollectionConverters._

/**
 * Canvas to draw a grid on.
 *
 * @author acdhirr
 * @param grid Game of life grid
 * @param zoom Magnification. A cell occupies zoom * zoom pixels
 * @param predictive Boolean whether predictive coloring is enabled
 */
class GridCanvas(var grid: Grid, val zoom: Int = 1, var predictive: Boolean = false) extends JComponent {

  override def getWidth: Int = grid.width * zoom
  override def getHeight: Int = grid.height * zoom

  private val liveColors = Array[Int](
    new Color(87, 128, 0).getRGB,
    new Color(105, 153, 0).getRGB,
    new Color(122, 179, 0).getRGB,
    new Color(136, 204, 0).getRGB,
    new Color(204, 255, 102).getRGB,
    new Color(204, 255, 102).getRGB,
    new Color(204, 255, 102).getRGB,
    new Color(221,255,153).getRGB,
    new Color(221,255,153).getRGB,
  )

  private val deadColors = Array[Int](
    new Color(40, 40, 40).getRGB,
    new Color(40, 40, 40).getRGB,
    new Color(40, 40, 40).getRGB,
    new Color(0, 51, 153).getRGB,
    new Color(40, 40, 40).getRGB,
    new Color(40, 40, 40).getRGB,
    new Color(40, 40, 40).getRGB,
    new Color(40, 40, 40).getRGB,
    new Color(40, 40, 40).getRGB
  )

  /**
   * Laod a grid on this canvas
   * @param g The Grid
   */
  def setGrid(g: Grid):Unit = {
    grid = g
    repaint()
  }

  /**
   * Enable or disable predictive coloring. When enabled, cell colors announce what they
   * will become in the next evolution.
   * @param p Boolean value whether to enable predictive colors
   */
  def setPredictive(p: Boolean) = {
    predictive = p
    repaint()
  }

  override def paintComponent(graphics: Graphics): Unit = {

    super.paintComponent(graphics)

    val width = getWidth
    val height = getHeight

    /* Draw an image depicting the grid
          x →
         +------------------
       y |          . (10,2)
       ↓ |
         |
         |
    */
    val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    for (
      x <- (0 until grid.width).par;
      y <- 0 until grid.height
    ) {
      grid(x,y) match {
        case LiveCell() => setPixels(x*zoom,y*zoom,zoom,img,getLiveColor(x,y,predictive))
        case _ => setPixels(x*zoom,y*zoom,zoom,img,getDeadColor(x,y,predictive))
      }
    }
    graphics.drawImage(img, 0, 0, null)
  }

  // Depending on predictive value, use a fixed color, or calculate the color
  // using the cells' neighbour count
  def getLiveColor(x:Int,y: Int, predictive: Boolean) = {
    if (!predictive) liveColors(2)
    else liveColors(grid.neighbourCount(x,y))
  }

  // Depending on predictive value, use a fixed color, or calculate the color
  // using the cells' neighbour count
  def getDeadColor(x:Int,y: Int, predictive: Boolean) = {
    if (!predictive) deadColors(0)
    else deadColors(grid.neighbourCount(x,y))
  }

  // Set as many pixels for a cell as the zoom size prescribes
  def setPixels(x: Int, y: Int, size: Int, img: BufferedImage, rgb:Int): Unit = {

      val colors = Array.fill[Int](size*size)(rgb)
      img.setRGB(x,y,size,size,colors,0,0)
  }

  // Click anywhere in the grid to toggle a cell
  addMouseListener(new MouseAdapter {
    override def mouseClicked(mouseEvent: MouseEvent): Unit = {
      setGrid(grid.toggle(mouseEvent.getX/zoom,mouseEvent.getY/zoom))
    }
  })

}

