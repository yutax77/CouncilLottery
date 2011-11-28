package com.yutax77.councilLottery

object CouncilLottery {
	def main(args: Array[String]): Unit = {
		if(args.length < 2){
			sys.exit(1)
		}
		
		val workers = WorkerListReader.read(args(0))
		val log = Log.createFromFile(args(1), workers)
		val counts = ExeCounts.create(log, workers)
		
		val chairmanScore = Scores.calcScores(counts.chairmans, log.newestNo)
		val secretaryScore = Scores.calcScores(counts.secretaries, log.newestNo)
		val snackScore = Scores.calcScores(counts.snackes, log.newestNo)
		
		val combinations = Combinations.calc(chairmanScore.toList, secretaryScore.toList, snackScore.toList)
		
		if(2 < args.length){
			Outputter.output(combinations, args(2).toInt)			
		}
		else{
			Outputter.output(combinations)
		}
	}

}