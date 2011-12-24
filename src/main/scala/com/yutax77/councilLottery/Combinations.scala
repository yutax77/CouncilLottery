package com.yutax77.councilLottery

case class Combinations(chairman: (Person, Double), secretary: (Person, Double), snacks: (Set[Person], Double)) {
	val score = chairman._2 + secretary._2 + snacks._2
	def toVector(rank: Int): java.util.Vector[String] = {
		val result = new java.util.Vector[String]()
		result.add(rank.toString)
		result.add("%.2f".format(score))
		result.add(chairman._1.name)
		result.add(secretary._1.name)
		result.add(snacks._1.map(_.name).mkString(", "))
		result
	}
}

object Combinations {
	def calc(chairmans: List[(Person, Double)],
			secretaries: List[(Person, Double)],
			snacks: List[(Person, Double)]
			): List[Combinations] = {
		val descChairmans = chairmans.sortBy(_._2).reverse
		val descSecretaries = secretaries.sortBy(_._2).reverse
		val descSnacks = snacks.sortBy(_._2).reverse

		def snackCombinations(chairman: Person, secretary: Person): List[(Set[Person], Double)] = {
			val pf:PartialFunction[(Person, Double), (Person, Double)] = {
				case p if p._1 != chairman && p._1 != secretary => p
			}
			
			val source = makeList(List.empty[(Person, Double)], descSnacks, pf)
			source.combinations(2).map{l => l.foldLeft(Tuple2(Set.empty[Person], 0.0)){(z, x) => (z._1 + x._1, z._2 + x._2)}}.toList
		}
		
		val result = for(chairman <- descChairmans;
			secretary <- descSecretaries; if secretary._1 != chairman._1;
			snacks <- snackCombinations(chairman._1, secretary._1))
		yield {
			Combinations(chairman, secretary, snacks)
		}
		
		result.toList
	}
	
	def makeList(l: List[(Person, Double)],
				source: List[(Person, Double)],
				pf: PartialFunction[(Person, Double), (Person, Double)]
				): List[(Person, Double)] = {
		require(!source.isEmpty)
		val result = (source.head :: l).collect(pf)
		if(result.size >= 2) {
			result
		}
		else{
			makeList(result, source.tail, pf)
		}				
	}	
}