package com.yutax77.councilLottery.gui

import scala.swing._
import scala.swing.event._
import java.io.File
import javax.swing.border.TitledBorder

class FilePanel(labelName: String) extends BoxPanel(Orientation.Horizontal) {
	val button = new Button {
		text = "..."
	}
		
	val fileName = new Label {
		maximumSize = new Dimension(350, 20)
	}
	
	val label = new Label {
		text = labelName
	}
	
	val chooser = new FileChooser
	
	name = labelName
	contents += label
	contents += fileName
	contents += button
	
	var file:File = null
	listenTo(button)
	reactions += {
		case ButtonClicked(b) => {
			val selected = chooser.showOpenDialog(null)
			file = chooser.selectedFile
			fileName.text = file.toString()
		}
	}
}