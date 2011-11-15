package com.yutax77.councilLottery

case class Score(person: Person, score: Double) {

}

object Score {
	
}

object Scores {
	def calcScore(counts: Map[Person, ExeCount]): Map[Person, Double] = {
		val max = counts.values.toList.maxBy(_.count).count
		
		def calcScoreImpl(l: List[(Person, ExeCount)], m: Map[Person, Double]): Map[Person, Double] = {
			if(l.isEmpty) {
				m
			}
			else {
				calcScoreImpl(l.tail, m + (l.head._1 -> calc(l.head._2.count)))
			}
		}
		
		def calc(count: Int): Double = {
			(1 / (1 + (count / max).toDouble))
		}
		
		calcScoreImpl(counts.toList, Map.empty[Person, Double])
	}
}