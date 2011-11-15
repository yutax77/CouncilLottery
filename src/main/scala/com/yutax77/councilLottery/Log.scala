package com.yutax77.councilLottery
import scala.collection.mutable.ListBuffer
import scala.collection._

case class Log(chairmans: List[Person], secretaries: List[Person], snackes: List[Set[Person]]) {
	require(chairmans.size == secretaries.size)
	require(secretaries.size == snackes.size)
	
	val chairmanCount = personCount(chairmans, Map.empty[Person, ExeCount], 1)
	val secretaryCount = personCount(secretaries, Map.empty[Person, ExeCount], 1)
	val snackCount = personSetCount(snackes, Map.empty[Person, ExeCount], 1)
	
	private def personCount(l: List[Person], m: Map[Person, ExeCount], no: Int): Map[Person, ExeCount] = {
		if(l.isEmpty) {
			m
		}
		else {
			val count = m.getOrElse(l.head, ExeCount(0, 0)).update(no)
			personCount(l.tail, m + (l.head -> count), no + 1)
		}
	}
	
	private def personSetCount(l: List[Set[Person]], m: Map[Person, ExeCount], no: Int): Map[Person, ExeCount] = {
		if(l.isEmpty) {
			m
		}
		else {
			val counts = personCount(l.head.toList, m, no)
			personSetCount(l.tail, counts, no + 1)
		}
	}
	
}

object Log {
	def create(elements: List[LogElement]): Log = {
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
	
}
case class LogElement(chairman: String,
						secretary: String,
						snack: Set[String])


case class ExeCount(count: Int, lastExpNo: Int) {
	def update(no: Int): ExeCount = {
		ExeCount(this.count + 1, no)
	}
}