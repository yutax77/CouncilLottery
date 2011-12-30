package com.yutax77.councilLottery.gui

import scala.swing.event.ButtonClicked
import scala.swing.Dimension
import scala.swing.BoxPanel
import scala.swing.Button
import scala.swing.MainFrame
import scala.swing.Orientation
import scala.swing.ScrollPane
import scala.swing.SimpleSwingApplication
import scala.swing.Table
import com.yutax77.councilLottery.Combinations
import com.yutax77.councilLottery.ExeCounts
import com.yutax77.councilLottery.Log
import com.yutax77.councilLottery.Scores
import com.yutax77.councilLottery.WorkerListReader
import com.yutax77.councilLottery.Outputter
import javax.swing.Box
import scala.swing.Swing

object Main extends SimpleSwingApplication {
	def top = new MainFrame {
		val fileSelector = new BoxPanel(Orientation.Vertical) {
			val worker = new FilePanel("Worker")
			val log = new FilePanel("Log")
			contents += worker
			contents += log
		}
		
		val spinners = new Spinners
		val runBtn = new Button {
			text = "Run"
		}
		val table = new Table {
			override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
			autoResizeMode = Table.AutoResizeMode.AllColumns
			model.addColumn("Rank")
			model.addColumn("Score")
			model.addColumn("議長")
			model.addColumn("書記")
			model.addColumn("おやつ係")
			
			model.addRow(Array[AnyRef]("", "", "", "", ""))
		}
		
		val panel = new ScrollPane(table)
		size = new Dimension(256, 212)
		
		contents = new BoxPanel(Orientation.Vertical) {
			contents += fileSelector
			contents += spinners
			contents += runBtn
			contents += Swing.Glue
			contents += panel
		}
		
		listenTo(runBtn)
		reactions += {
			case ButtonClicked(b) => {
				val workers = WorkerListReader.read(fileSelector.worker.file.toString())
				val log = Log.createFromFile(fileSelector.log.file.toString())
				val counts = ExeCounts.create(log, workers)
				
				val chairmanScore = Scores.calcScores(counts.chairmans, log.newestNo)
				val secretaryScore = Scores.calcScores(counts.secretaries, log.newestNo)
				val snackScore = Scores.calcScores(counts.snackes, log.newestNo)
				
				val combinations = Combinations.calc(chairmanScore.toList, secretaryScore.toList, snackScore.toList)				
				val sorted = combinations.sortBy(_.score).reverse
				val sortedRank = Outputter.calcRank(sorted, List[(Int, Combinations)](), Double.MaxValue, 1, 1)
				
				table.model.setRowCount(0)//直前の実行結果をクリア
				sortedRank.filter(_._1 < spinners.spinner.model.getValue().asInstanceOf[Int]).foreach(pair => table.model.addRow(pair._2.toVector(pair._1)))
			}
		}
	}
}