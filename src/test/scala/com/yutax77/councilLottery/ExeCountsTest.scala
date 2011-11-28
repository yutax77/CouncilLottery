package com.yutax77.councilLottery
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter

@RunWith(classOf[JUnitRunner])
class ExeCountsTest extends FunSuite with ShouldMatchers {
	val log = Log(List(Person("Miles")), List(Person("Herbie")), List(Set(Person("Ron"), Person("Tony"))))
	val workers = Set(Person("Miles"), Person("Herbie"), Person("Tony"), Person("Wanye"))
	
	test("Retirees should be removed from the result And Unexperienced should be included.") {
		val actual = ExeCounts.create(log, workers)
		val expected = ExeCounts(Map(Person("Miles") -> ExeCount(1, 1), Person("Herbie") -> ExeCount(0, 0), Person("Tony") -> ExeCount(0, 0), Person("Wanye") -> ExeCount(0, 0)), 
									Map(Person("Herbie") -> ExeCount(1, 1), Person("Miles") -> ExeCount(0, 0), Person("Tony") -> ExeCount(0, 0), Person("Wanye") -> ExeCount(0, 0)),
									Map(Person("Tony") -> ExeCount(1, 1), Person("Miles") -> ExeCount(0, 0), Person("Herbie") -> ExeCount(0, 0), Person("Wanye") -> ExeCount(0, 0)))
									
		actual.chairmans should be (expected.chairmans)
		actual.secretaries should be (expected.secretaries)
		actual.snackes should be (expected.snackes)
	}
}