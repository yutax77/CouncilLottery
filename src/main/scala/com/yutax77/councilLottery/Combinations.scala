package com.yutax77.councilLottery

case class Combinations(chairman: (Person, Double), secretary: (Person, Double), snacks: (Set[Person], Double)) {
	val score = chairman._2 + secretary._2 + snacks._2
}

object Combinations {
	def calc(chairmans: List[(Person, Double)], secretaries: List[(Person, Double)], snacks: List[(Person, Double)]): List[Combinations] = {
		val descChairmans = chairmans.sortBy(_._2).reverse.groupBy(_._2)
		val descSecretaries = secretaries.sortBy(_._2).reverse.groupBy(_._2)
		val descSnacks = snacks.sortBy(_._2).reverse.groupBy(_._2).toList
		
		def snackCombinations(chairman: Person, secretary: Person): List[(Set[Person], Double)] = {
			val pf:PartialFunction[(Person, Double), (Person, Double)] = {
				case p if p._1 != chairman && p._1 != secretary => p
			}
			
			def makeList(l: List[(Person, Double)], source: List[(Double, List[(Person, Double)])]): List[(Person, Double)] = {
				val result = l ::: source.head._2.collect(pf)
				if(result.size > 2) {
					result
				}
				else{
					makeList(result, source.tail)
				}				
			}
			
			val source = makeList(List.empty[(Person, Double)], descSnacks)
			source.combinations(2).map{l => l.foldLeft(Tuple2(Set.empty[Person], 0.0)){(z, x) => (z._1 + x._1, z._2 + x._2)}}.toList
		}
		
		val result = for(chairmanInfo <- descChairmans;
			chairman <- chairmanInfo._2;
			secretaryInfo <- descSecretaries;
			secretary <- secretaryInfo._2; if secretary != chairman;
			snacks <- snackCombinations(chairman._1, secretary._1))
		yield {
			Combinations(chairman, secretary, snacks)
		}
		
		result.toList
	}
	
	
}