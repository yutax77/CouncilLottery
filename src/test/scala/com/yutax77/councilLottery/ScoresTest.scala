package com.yutax77.councilLottery
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScoresTest extends FunSuite with ShouldMatchers {
	test("Calc NO　experience"){
		val input = Map(Person("hoge") -> ExeCount(0, 0), Person("foo") -> ExeCount(1, 1))
		val actual = Scores.calcScores(input, 1)
		actual should be (Map(Person("hoge") -> 2.0, Person("foo") -> 0.5))
	}
	
	test("NewestNo shoud be larger than 0"){
		val input = Map(Person("hoge") -> ExeCount(1,1))
		intercept[IllegalArgumentException]{
			Scores.calcScores(input, 0)
		}
	}
}