package gameoflife.gui.swing

import gameoflife.model._
import gameoflife.patterns.{HardcodedPatterns, ResourcePatterns}

import java.awt.{BorderLayout, Color, Dimension, GridLayout, Toolkit}
import javax.swing._
import javax.swing.plaf.FontUIResource
import javax.swing.UIManager
import java.awt.Font
import javax.swing.event.{ChangeEvent, ChangeListener}

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

    // Initialization -------------------------------
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setLayout(new BorderLayout)
    setUIFont(new FontUIResource("Sans Serif", Font.PLAIN, 11)) // see method below

    // save initial grid to be displayed on reset
    var startGrid = grid

    // Constants ------------------------------------
    val rightpanelWidth = 150
    val canvas = new GridCanvas(grid, zoom)
    val dim = new Dimension(canvas.getWidth + rightpanelWidth, canvas.getHeight)

    // Rightpanel: Side bar right contains controls area --------------------
    val rightpanel = new JPanel
    rightpanel.setBorder(BorderFactory.createEtchedBorder(border.EtchedBorder.LOWERED))
    val rdim = new Dimension(rightpanelWidth, canvas.getHeight)
    rightpanel.setPreferredSize(rdim)
    rightpanel.setMaximumSize(rdim)
    rightpanel.setMinimumSize(rdim)
    rightpanel.setLayout(new BorderLayout)
    add(rightpanel, BorderLayout.EAST)

    // Controls area --------------------
    val controls = new JPanel
    controls.setLayout(new GridLayout(0, 1))
    rightpanel.add(controls, BorderLayout.NORTH)

    // Control: Pattern picker dialog -----------------------------------
    val dialog = new LibraryDialog(this, "/library.txt")
    val patternsButton = new JButton("Library")
    patternsButton.addActionListener( _ => {
      dialog.setVisible(true)
    })
    controls.add(patternsButton)

    // Control: Separator ----------------------------------
    controls.add(new JSeparator(SwingConstants.HORIZONTAL))

    // Control: Soup settings ----------------------
    val spinnerBox = new JPanel()
    spinnerBox.setLayout(new GridLayout(0, 2))
    val spinnerText = new JLabel("Soup ratio")
    spinnerBox.add(spinnerText)
    val bodiesSpinner = new JSpinner(new SpinnerNumberModel(15, 2, 40, 1))
    spinnerBox.add(bodiesSpinner)
    controls.add(spinnerBox)
    val soupButton = new JButton("Random Soup")
    soupButton.addActionListener(_ => {
      setRunning(false)
      resetWithGrid(soupPopulatedGrid(grid.width, grid.height, bodiesSpinner.getValue.asInstanceOf[Int]))
    })
    controls.add(soupButton)

    // Control: Separator ----------------------------------
    controls.add(new JSeparator(SwingConstants.HORIZONTAL))

    // Control: Start/pause evolution ----------------------
    val startButton = new JToggleButton("▶")
    val startTimer = new javax.swing.Timer(50, _ => step())
    startButton.addActionListener( _ => {
      if (startButton.isSelected) setRunning(true)
      else setRunning(false)
    })
    controls.add(startButton)

    // Control: Step evolution ----------------------
    val stepButton = new JButton("Step")
    stepButton.addActionListener(_ => {
      setRunning(false)
      step()
    })
    controls.add(stepButton)

    // Control: Reset evolution ----------------------
    val resetButton = new JButton("Reset")
    resetButton.addActionListener(_ => {
      setRunning(false)
      resetWithGrid(startGrid)
    })
    controls.add(resetButton)

    // Control: Clear grid ----------------------
    val clearButton = new JButton("Clear")
    clearButton.addActionListener( _ => {
      setRunning(false)
      canvas.setGrid(grid.clear())
    })
    controls.add(clearButton)

    // Control: Separator --------------------------------------
    controls.add(new JSeparator(SwingConstants.HORIZONTAL))

    // Control: Predictive coloring checkbox --------------------
    val cbPredictive = new JCheckBox("Predictive colors")
    cbPredictive.addActionListener(_ => {
      if (cbPredictive.isSelected) canvas.setPredictive(true)
      else canvas.setPredictive(false)
    })
    controls.add(cbPredictive)

    // Control: Statistics info box ----------------------
    val infoBox = new GridInfo
    infoBox.setRows(2)
    infoBox.setBorder(BorderFactory.createLoweredBevelBorder)
    infoBox.setBorder(BorderFactory.createEmptyBorder(10,10,10,10))
    infoBox.setBackground(new Color(0,0,0))
    infoBox.setForeground(new Color(0,200,0))
    rightpanel.add(infoBox, BorderLayout.SOUTH)

    // Positioning and wrapping up -------------------------------
    setResizable(false)
    getContentPane.setPreferredSize(dim)
    add(canvas, BorderLayout.CENTER)
    pack()
    // center on screen
    val screen: Dimension = Toolkit.getDefaultToolkit.getScreenSize
    setLocation(screen.width / 2 - getSize().width / 2, screen.height / 2 - getSize().height / 2)
    setVisible(true)

    // Function step: advance to next evolution
    def step(): Unit = {
      val nextGrid = canvas.grid.nextGeneration()
      infoBox.updateStatistics(nextGrid)
      canvas.setGrid(nextGrid)
    }

    def setRunning(enabled: Boolean) = {
      if (enabled) {
        startTimer.start()
        startButton.setText("⏸")
      }
      else {
        startTimer.stop()
        startButton.setText("▶")
      }
      startButton.setSelected(enabled)
    }

    // Function step: advance to next evolution
    def resetWithGrid(grid: Grid): Unit = {
      infoBox.reset()
      startGrid = grid
      canvas.setGrid(grid)
    }

    // Load a grid from the library
    def getGridByName(name:String) : Grid = {
      val pattern = new ResourcePatterns("/library.txt").getPattern(name)
      val coordinates = HardcodedPatterns.getLiveCellsFromPattern(pattern,(grid.width-pattern.width)/2,(grid.height-pattern.height)/2)
      coordinatesPopulatedGrid(grid.width, grid.height, coordinates)
    }

    // Set a (custom) font
    def setUIFont(f: FontUIResource): Unit = {
      val keys = UIManager.getDefaults.keys
      while ( {
        keys.hasMoreElements
      }) {
        val key = keys.nextElement
        val value = UIManager.get(key)
        if (value.isInstanceOf[FontUIResource]) UIManager.put(key, f)
      }
    }

  }

  try {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  } catch {
    case _: Exception => println("Cannot set look and feel, using the default one.")
  }


  /**
   * Main execution method
   */
  def main(args: Array[String]): Unit = {

    val gridSize = (300,200)
    val game = new GOLFrame( soupPopulatedGrid(gridSize._1, gridSize._2, 15),3)
    game.repaint()
  }

}


