package model

import gameoflife.model.{Cell, DeadCell, Grid, LiveCell}
import org.junit.Assert.{assertArrayEquals, assertEquals, assertThat}
import org.junit.Test
import org.scalatest.Assertions.withClue
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class GridTests {

  val toad_cells: Array[Cell] = Array( // a 'toad'
    DeadCell(),LiveCell(),LiveCell(),LiveCell(),
    LiveCell(),LiveCell(),LiveCell(),DeadCell())

  val toad_cells_nextgen: Array[Cell] = Array( // a 'toad'
    LiveCell(),DeadCell(),DeadCell(),LiveCell(),
    LiveCell(),DeadCell(),DeadCell(),LiveCell())

  /* toad:
     .OOO
     OOO.

     toad nextgen: (note that the pattern will not exceed the playing field)
     O..O
     O..O
  */

  @Test def test_grid_coordinates_after_instantiation: Unit =  {

    val grid = new Grid(4,2, toad_cells)

    assertEquals("First cell first row must be a dead cell", DeadCell(), grid(0,0) )
    assertEquals("First cell second row must be a live cell", LiveCell(), grid(0,1) )
  }

  @Test def test_grid_toggle_cell: Unit =  {

    val grid = new Grid(4,2, toad_cells)
    val grid2 = grid.toggle(0,0).toggle(1,0)
    assertEquals("Toggling must invert DeadCell", LiveCell(), grid2(0,0))
    assertEquals("Toggling must invert LiveCell", DeadCell(), grid2(1,0))
  }

  @Test def test_clear_grid: Unit =  {

    val grid = new Grid(4,2, toad_cells)
    val grid2 =  grid.clear()
    val liveCells: Option[Cell] = grid2.cells.find(cell => cell.isInstanceOf[LiveCell])
    assert( liveCells.isEmpty, "Call to clear() must return an empty grid" )
  }

  @Test def test_neighbour_count: Unit =  {

    val grid = new Grid(4,2, toad_cells)
    val count = grid.neighbourCount(2,0)
    assertEquals("Neighbours must be counted correctly", 4, count)
  }

  @Test def test_next_generation: Unit =  {

    val grid = new Grid(4,2, toad_cells)
    val grid2 = grid.nextGeneration()
    // ScalaTest syntax see https://www.scalatest.org/
    withClue("Next generation array does not match expected array:\n ") {
      grid2.cells should equal (toad_cells_nextgen) // array comparison
    }
  }

}