package com.yutax77.councilLottery
import scala.collection.mutable.ListBuffer
import scala.io.Source
import com.codahale.jerkson.Json._

case class Log(chairmans: List[Person], secretaries: List[Person], snackes: List[Set[Person]]) {
	require(chairmans.size == secretaries.size)
	require(secretaries.size == snackes.size)
	val newestNo = chairmans.size
}

object Log {
	def create(elements: List[LogElement], workers: Set[Person]): Log = {
		val chairmans = new ListBuffer[Person]
		val secretaries = new ListBuffer[Person]
		val snackes = new ListBuffer[Set[Person]]
		
		def makeElements(element: LogElement): Unit = {
			chairmans += Person(element.chairman)
			secretaries += Person(element.secretary)
			snackes += (Set[Person]() ++ (element.snack map (Person(_))))
		}
		
		elements foreach (makeElements _)
		new Log(chairmans.toList, secretaries.toList, snackes.toList)
	}
	
	def createFromFile(file: String, workers: Set[Person]): Log = {
		val source = Source.fromFile(file)
		try {
			val fromJson = parse[List[LogElement]](source)
			Log.create(fromJson, workers)
		} finally {
			source.close
		}
	}
}

case class LogElement(chairman: String,
						secretary: String,
						snack: Set[String])
