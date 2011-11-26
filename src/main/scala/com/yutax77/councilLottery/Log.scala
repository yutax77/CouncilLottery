package com.yutax77.councilLottery
import scala.collection.mutable.ListBuffer
import scala.io.Source
import com.codahale.jerkson.Json._

case class Log(chairmans: List[Person], secretaries: List[Person], snackes: List[Set[Person]], workers: Set[Person]) {
	require(chairmans.size == secretaries.size)
	require(secretaries.size == snackes.size)
	
	val chairmanCount = updateCount(personCount(chairmans, Map.empty[Person, ExeCount], 1))
	val secretaryCount = updateCount(personCount(secretaries, Map.empty[Person, ExeCount], 1))
	val snackCount = updateCount(personSetCount(snackes, Map.empty[Person, ExeCount], 1))
	
	
	private def addInexperienced(l: List[Person], m: Map[Person, ExeCount]): Map[Person, ExeCount] = {
		if(l.isEmpty){
			m
		}
		else{
			addInexperienced(l.tail, m + (l.head -> ExeCount(0,0)))
		}
	}
	
	private def updateCount(m: Map[Person, ExeCount]): Map[Person, ExeCount] = {
		//未経験者を追加
		val inexperienced = workers diff m.keySet toList
		val r = addInexperienced(inexperienced, m)

		//WorkerListに存在しない退職者を取り除く
		val retiree = m.keySet diff workers
		r -- retiree
	}
	
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
		new Log(chairmans.toList, secretaries.toList, snackes.toList, workers)
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


case class ExeCount(count: Int, lastExpNo: Int) {
	def update(no: Int): ExeCount = {
		ExeCount(this.count + 1, no)
	}
}