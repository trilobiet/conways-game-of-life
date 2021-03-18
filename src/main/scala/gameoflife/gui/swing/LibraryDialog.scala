package gameoflife.gui.swing

import gameoflife.gui.swing.GameOfLife.GOLFrame
import gameoflife.patterns.{Pattern, ResourcePatterns}

import java.awt.Dialog
import java.awt.event.{KeyAdapter, KeyEvent, MouseAdapter, MouseEvent}
import javax.swing.{JDialog, JFrame, JScrollPane, JTree}
import javax.swing.tree.DefaultMutableTreeNode
import scala.collection.immutable.ListMap

class LibraryDialog(val owner: GOLFrame, val libraryPath: String) extends JDialog {

  setModalityType(Dialog.ModalityType.DOCUMENT_MODAL)
  setTitle("Patterns Library")
  setSize(400,300)
  setLocationRelativeTo(owner)
  setModal(true)

  val top = new DefaultMutableTreeNode("Game of Life Pattern Library");
  createNodes(top)
  val tree = new JTree(top)
  val pane = new JScrollPane(tree);
  add(pane)

  // selection handling
  tree.addMouseListener(new MouseAdapter {
    override def mousePressed(e:MouseEvent): Unit =
      if (e.getClickCount ==2) selectInTree(tree) else {}
  })

  tree.addKeyListener(new KeyAdapter {
    override def keyPressed(e: KeyEvent): Unit =
      if (e.getKeyCode == 10)  selectInTree(tree) else {}
  })

  private def selectInTree(tree: JTree) = {
    val node = tree.getLastSelectedPathComponent().asInstanceOf[DefaultMutableTreeNode]
    if (node != null && node.isLeaf) {
      owner.setRunning(false)
      owner.resetWithGrid(owner.getGridByName(node.getUserObject.toString))
      this.dispose()
    }
  }

  /* useful for showing metadata when scrolling through tree:
  tree.addTreeSelectionListener(new TreeSelectionListener() {
    override def valueChanged(treeSelectionEvent: TreeSelectionEvent): Unit = ...
  })
  */

  private def createNodes(top: DefaultMutableTreeNode): Unit = {

    patterns(libraryPath).foreach {
      case(char, list) => {
        val letter = new DefaultMutableTreeNode(char.toString)
        top.add(letter)
        list.foreach(name => letter.add(new DefaultMutableTreeNode(name)))
      }
    }
  }

  private def patterns(path: String): Map[Char, Iterable[String]]  = {

    val lib: ResourcePatterns = new ResourcePatterns(path)
    val patterns: Map[String, Pattern] = lib.getPatterns()

    val map = patterns
      .map{ case(name,_) => name}
      .groupBy(n => n.charAt(0))

    ListMap(map.toSeq.sortBy(_._1):_*) // sort by key
  }



}

