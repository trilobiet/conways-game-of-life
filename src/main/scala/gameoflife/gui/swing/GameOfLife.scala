package gameoflife.gui.swing

import gameoflife.model._
import gameoflife.patterns
import gameoflife.patterns.{HardcodedPatterns, ResourcePatterns}

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Color, Dimension, GridLayout, Toolkit}
import javax.swing._
import javax.swing.{BorderFactory, JFrame, JPanel, WindowConstants}

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
    val startGrid = grid

    // Constants ------------------------------------
    val rightpanelWidth = 150
    val canvas = new GridCanvas(grid, zoom)
    val dim = new Dimension(canvas.getWidth + rightpanelWidth, canvas.getHeight)

    // Rightpanel: controls -------------------------
    val rightpanel = new JPanel
    rightpanel.setBorder(BorderFactory.createEtchedBorder(border.EtchedBorder.LOWERED))
    val rdim = new Dimension(rightpanelWidth, canvas.getHeight)
    rightpanel.setPreferredSize(rdim)
    rightpanel.setMaximumSize(rdim)
    rightpanel.setMinimumSize(rdim)
    rightpanel.setLayout(new BorderLayout)
    rightpanel.setBackground(Color.lightGray)
    add(rightpanel, BorderLayout.EAST)

    val controls = new JPanel
    controls.setLayout(new GridLayout(0, 1))
    rightpanel.add(controls, BorderLayout.NORTH)

    // Control: Step evolution ----------------------
    val stepButton = new JButton("Step")
    stepButton.addActionListener(_ => step())
    controls.add(stepButton)

    // Control: Start/pause evolution ----------------------
    val startButton = new JToggleButton("Start/Pause")
    val startTimer = new javax.swing.Timer(20, _ => step())
    startButton.addActionListener( _ => {
      if (startButton.isSelected) startTimer.start()
      else startTimer.stop()
    })
    controls.add(startButton)

    // Control: Reset evolution ----------------------
    val resetButton = new JButton("Reset")
    resetButton.addActionListener(_ => reset())
    controls.add(resetButton)

    // Control: Statistics info box ----------------------
    val infoBox = new GridInfo
    infoBox.setRows(2)
    infoBox.setBorder(BorderFactory.createLoweredBevelBorder)
    infoBox.setBorder(BorderFactory.createEmptyBorder(10,10,10,10))
    infoBox.setBackground(new Color(0,0,0))
    infoBox.setForeground(new Color(0,200,0))
    rightpanel.add(infoBox, BorderLayout.SOUTH)

    // Control: Pattern pickers -----------------------------------
    val patternsLib = new ResourcePatterns("/library.txt")
    val letters = patternsLib.getLetters().map(_.toString).toArray
    val letterCombo = new JComboBox[String](letters)
    letterCombo.addActionListener( new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = {
        val letter = letterCombo.getItemAt(letterCombo.getSelectedIndex)
        val patterns: Array[String] = patternsLib.getPatterns(letter).map(_._1).toArray
        patternsCombo.setModel(new DefaultComboBoxModel[String](patterns))
      }
    })
    controls.add(letterCombo)
    val patternsCombo = new JComboBox[String]()
    controls.add(patternsCombo)

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
    def step(): Unit =
      SwingUtilities.invokeLater(() => {
        val nextGrid = canvas.grid.nextGeneration()
        infoBox.updateStatistics(nextGrid)
        canvas.setGrid(nextGrid)
      })

    // Function step: advance to next evolution
    def reset(): Unit = {
      startTimer.stop()
      infoBox.reset()
      canvas.setGrid(startGrid)
    }

  }

  try {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  } catch {
    case _: Exception => println("Cannot set look and feel, using the default one.")
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
    // val coordinates = PatternLib.getPatternArray(PatternLib.hammerhead,275,30)
    // val game = new GOLFrame( coordinatesPopulatedGrid(300,80, coordinates), 3)

    val pattern = new ResourcePatterns("/library.txt").getPattern("gliders_by_the_dozen")
    val screenSize = (300,200)
    val coordinates = HardcodedPatterns.getLiveCellsFromPattern(pattern,(screenSize._1-pattern.width)/2,(screenSize._2-pattern.height)/2)
    println(s"Pattern width=${pattern.width}; height=${pattern.height}")
    val game = new GOLFrame( coordinatesPopulatedGrid(300,200, coordinates), 4)
    game.repaint()
  }
}
