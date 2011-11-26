package com.yutax77.councilLottery

case class Score(person: Person, score: Double) {

}

object Scores {
	def calcScores(counts: Map[Person, ExeCount], newestNo: Int): Map[Person, Double] = {
		val max = counts.values.toList.maxBy(_.count).count
		def calcScore(count: Int): Double = (1.0 / (1.0 + (count.toDouble / max)))
		def calcElapsedTimeScore(lastExpNo: Int): Double = (1.0 - (lastExpNo.toDouble / newestNo))
		
		def calcScoreBase(l: List[(Person, ExeCount)], m: Map[Person, Double]): Map[Person, Double] = {
			if(l.isEmpty) {
				m
			}
			else {
				calcScoreBase(l.tail, m + (l.head._1 -> (calcScore(l.head._2.count) + calcElapsedTimeScore(l.head._2.lastExpNo))))
			}
		}	
		calcScoreBase(counts.toList, Map.empty[Person, Double])
	}
}