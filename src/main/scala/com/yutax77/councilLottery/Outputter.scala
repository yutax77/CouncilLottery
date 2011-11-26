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
		
		var count = 1
		var rank = 1
		var score = Double.MaxValue
		breakable{
			sorted.foreach{comb =>
				if(comb.score < score){
					rank += (count - rank)
				}
				
				if(maxRank < rank){
					break
				}
				
				count += 1
				score = comb.score
				print(comb, rank)
			}			
		}
	}
}