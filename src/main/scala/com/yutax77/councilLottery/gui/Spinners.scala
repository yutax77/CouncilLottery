package com.yutax77.councilLottery.gui

import scala.swing.BoxPanel
import scala.swing.Orientation
import scala.swing.Label
import javax.swing._
import scala.swing.Component
import java.awt.Dimension

class Spinners extends BoxPanel(Orientation.Horizontal) {
	val label = new Label {
		text = "最大表示順位"
	}
	
	val spinner = new Spinner(20, 1, 100, 1)
	spinner.maximumSize = new Dimension(300, 20)
	
	contents += label
	contents += spinner
}

class Spinner(value: Int, start: Int, end: Int, step: Int) extends Component{
	override lazy val peer = new JSpinner() with SuperMixin
	val model = new SpinnerNumberModel(value, start, end, step)
	peer.setModel(model)
}