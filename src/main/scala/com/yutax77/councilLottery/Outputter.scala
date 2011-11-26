package com.yutax77.councilLottery

object Outputter {
	private val defaultMaxRank = 20
	
	def output(results: List[Combinations], maxRank: Int = defaultMaxRank) = {
		def print(combinations: Combinations, rank: Int) = {
			val buf = new StringBuilder()
			buf.append(rank) + '\t'
			buf.append("%.2f".format(combinations.score)) + '\t'
			buf.append(combinations.chairman) + '\t'
			buf.append(combinations.secretary) + '\t'
			
			for(snack <- combinations.snacks._1){
				buf.append(snack).append("  ")
			}
			
			println(buf)
		}
		
		println("Rank \tScore \t議長 \t書記 \tおやつ係");
		println("---------------------------------------------------");
		
		val sorted = results.sortBy(_.score)
		
		var count = 0
		var rank = 1
		var score = Double.MaxValue
		sorted.takeWhile{comb => 
				if(comb.score < score){
					rank += count - rank
				}
				count += 1
				score = comb.score
				rank < maxRank
			}.foreach(	print(_, rank))
	}
}