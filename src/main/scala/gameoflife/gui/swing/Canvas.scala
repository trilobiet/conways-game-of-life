package gameoflife.gui.swing

import gameoflife.model.{Grid, LiveCell}

import java.awt.image.BufferedImage
import java.awt.{Color, Graphics}
import javax.swing.JComponent

class Canvas(var grid: Grid, val zoom: Int = 1) extends JComponent {

  override def getWidth: Int = grid.width * zoom
  override def getHeight: Int = grid.height * zoom

  def setGrid(g: Grid) = {
    grid = g
    repaint()
  }

  override def paintComponent(gcan: Graphics) = {

    super.paintComponent(gcan)

    val width = getWidth
    val height = getHeight
    val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    /*
          x →
         +------------------
       y |          . (10,2)
       ↓ |
         |
         |
    */

    // img.setRGB(205,100,new Color(255, 0, 0).getRGB)

    val life = new Color(0, 190, 250).getRGB
    val dead = new Color(40, 40, 40).getRGB
    for (x <- 0 until grid.width; y <- 0 until grid.height) {
      grid(x,y) match {
        //case LiveCell() => img.setRGB(x,y,life)
        //case _ => img.setRGB(x,y,dead)
        case LiveCell() => setPixels(x*zoom,y*zoom,zoom,img,life)
        case _ => setPixels(x*zoom,y*zoom,zoom,img,dead)
      }
    }

    gcan.drawImage(img, 0, 0, null)
  }

  def setPixels(x: Int, y: Int, size: Int, img: BufferedImage, rgb:Int): Unit = {

      val colors = Array.fill[Int](size*size)(rgb)
      img.setRGB(x,y,size,size,colors,0,0)
  }


}

