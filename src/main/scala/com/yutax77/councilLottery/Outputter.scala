package com.yutax77.councilLottery

import scala.util.control.Breaks._

object Outputter {
	private val defaultMaxRank = 20
	
	def output(results: List[Combinations], maxRank: Int = defaultMaxRank) = {
		def print(combinations: Combinations, rank: Int) = {
			val buf = new StringBuilder()
			buf.append(rank) + '\t'
			buf.append("%.2f".format(combinations.score)) + '\t'
			buf.append(combinations.chairman._1.name) + '\t'
			buf.append(combinations.secretary._1.name) + '\t'
			
			for(snack <- combinations.snacks._1){
				buf.append(snack.name).append("  ")
			}
			
			println(buf)
		}
		
		println("Rank \tScore \t議長 \t書記 \tおやつ係");
		println("---------------------------------------------------");
		
		val sorted = results.sortBy(_.score).reverse
		val sortedRank = calcRank(sorted, List[(Int, Combinations)](), Double.MaxValue, 1, 1)
		sortedRank.filter(_._1 < maxRank).foreach(pair => print(pair._2, pair._1))
	}
	
	def calcRank(l: List[Combinations], result: List[(Int, Combinations)], score: Double, rank: Int, count: Int): List[(Int, Combinations)] = {
		if(l.isEmpty){
			result			
		}
		else{
			if(l.head.score < score){
				calcRank(l.tail, result ::: List((count, l.head)), l.head.score, count, count + 1)
			}
			else{
				calcRank(l.tail, result ::: List((rank, l.head)), l.head.score, rank, count + 1)
			}
		}
	}
}