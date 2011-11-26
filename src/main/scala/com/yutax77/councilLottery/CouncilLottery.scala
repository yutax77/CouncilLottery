package com.yutax77.councilLottery

object CouncilLottery {
	def main(args: Array[String]): Unit = {
		if(args.length < 2){
			exit(1)
		}
		
		val workers = WorkerListReader.read(args(0))
		val log = Log.createFromFile(args(1), workers)
		
		val newestNo = log.chairmanCount.size
		val chairmanScore = Scores.calcScores(log.chairmanCount, newestNo)
		val secretaryScore = Scores.calcScores(log.secretaryCount, newestNo)
		val snackScore = Scores.calcScores(log.snackCount, newestNo)
		
		val combinations = Combinations.calc(chairmanScore.toList, secretaryScore.toList, snackScore.toList)
		
		if(2 < args.length){
			Outputter.output(combinations, args(2).toInt)			
		}
		else{
			Outputter.output(combinations)
		}
	}

}