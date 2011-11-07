package com.yutax77.councilLottery
import com.yutax77.councilLottery._
import scala.io.Source
import scala.collection.mutable
import scala.collection.immutable.HashSet

object WorkerListReader {
	def read(file: String): Set[Person] = {
		val source = Source.fromFile(file)
		val buf = mutable.Set.empty[Person]

		try {
			for (line <- source.getLines) {
				buf += Person(line)
			}
			return Set.empty ++ buf
		} finally {
			source.close
		}
	}
}