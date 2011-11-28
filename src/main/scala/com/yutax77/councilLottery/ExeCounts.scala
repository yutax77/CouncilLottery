package com.yutax77.councilLottery

case class ExeCounts(chairmans: Map[Person, ExeCount],
						secretaries: Map[Person, ExeCount],
						snackes: Map[Person, ExeCount])

object ExeCounts {
	def create(log: Log, workers: Set[Person]) = {
		def updateCount(m: Map[Person, ExeCount]): Map[Person, ExeCount] = {
			//未経験者を追加
			val inexperienced = workers diff m.keySet toList
			val r = addInexperienced(inexperienced, m)
	
			//WorkerListに存在しない退職者を取り除く
			val retiree = m.keySet diff workers
			r -- retiree
		}
	
		val chairmanCount = updateCount(personCount(log.chairmans, Map.empty[Person, ExeCount], 1, _ + 1))
		val secretaryCount = updateCount(personCount(log.secretaries, Map.empty[Person, ExeCount], 1, _ + 1))
		val snackCount = updateCount(personSetCount(log.snackes, Map.empty[Person, ExeCount], 1))
		
		ExeCounts(chairmanCount, secretaryCount, snackCount)
	}
	
	private def addInexperienced(l: List[Person], m: Map[Person, ExeCount]): Map[Person, ExeCount] = {
		if(l.isEmpty){
			m
		}
		else{
			addInexperienced(l.tail, m + (l.head -> ExeCount(0,0)))
		}
	}
	
	private def personCount(l: List[Person], m: Map[Person, ExeCount], no: Int, f: Int => Int): Map[Person, ExeCount] = {
		if(l.isEmpty) {
			m
		}
		else {
			val count = m.getOrElse(l.head, ExeCount(0, 0)).update(no)
			personCount(l.tail, m + (l.head -> count), f(no), f)
		}
	}
	
	private def personSetCount(l: List[Set[Person]], m: Map[Person, ExeCount], no: Int): Map[Person, ExeCount] = {
		if(l.isEmpty) {
			m
		}
		else {
			val counts = personCount(l.head.toList, m, no, x => x)
			personSetCount(l.tail, counts, no + 1)
		}
	}	
}

case class ExeCount(count: Int, lastExpNo: Int) {
	def update(no: Int): ExeCount = {
		ExeCount(this.count + 1, no)
	}
}