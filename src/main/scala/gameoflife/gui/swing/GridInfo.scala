package gameoflife.gui.swing

import gameoflife.model
import gameoflife.model.Grid

import javax.swing.JTextArea
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
 * A JTextArea that shows statistics info for a grid.
 * This class holds a lot of state.
 * Since Grid is immutable, you must repeatedly pass in a new grid to build up
 * a history on which to base the statistics.
 */
class GridInfo extends JTextArea {

  private var newCount = Future(0)
  private var lastCount = 0
  private var generation = 0

  private def incGenerationCount = {
    generation = generation + 1
    generation
  }

  /**
   * Count the Live cells in a Grid
   * Call to this function are best wrapped in a Future to prevent the
   * counting from blocking the main thread
   *
   * @param grid Grid to be counted
   * @return
   */
  private def countCells(grid: Grid) = {
    grid.cells.foldLeft(0)((count, cell) => {
      count + (cell match {
        case model.DeadCell() => 0
        case model.LiveCell() => 1
      })
    })
  }

  /**
   * Set the TextArea text
   * @param generation Generation number
   * @param cells Number of live cells
   */
  private def updateText(generation:Int,cells:Int) = {
    setText(
      "gen: " + generation + "\n" +
      "cells: " + cells
    )
  }

  /**
   * Show statistic info for a grid
   * @param grid Grid to show statistics for
   */
  def updateStatistics(grid: Grid) = {

    updateText(incGenerationCount,lastCount)

    // Skip countCells when a previous call is still busy
    if (newCount.isCompleted) {

      newCount = Future(countCells(grid))

      newCount.onComplete {
        case Success(value) => {
          lastCount = value
          updateText(generation,lastCount)
        }
      }
    }
  }

  def reset(): Unit = {
    newCount = Future(0)
    lastCount = 0
    generation = 0
    updateText(0,0)
  }


}
