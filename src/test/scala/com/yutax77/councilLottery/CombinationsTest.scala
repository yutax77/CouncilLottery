package com.yutax77.councilLottery
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CombinationsTest extends FunSuite with ShouldMatchers {
	test("Calc rank"){
		val chairmans = List((Person("bar1"), 2.0), (Person("bar2"), 1.5), (Person("bar3"), 1.0), (Person("bar4"), 0.5))
		val secretaries = List((Person("bar2"), 2.0), (Person("bar3"), 1.8), (Person("bar4"), 1.3), (Person("bar1"), 0.8))
		val snacks = List((Person("bar3"), 2.0), (Person("bar4"), 1.2), (Person("bar1"), 0.8), (Person("bar2"), 0.3))
		
		val result = Combinations.calc(chairmans, secretaries, snacks)
		
		result should have length (12)
		result should contain(Combinations((Person("bar1"), 2.0), (Person("bar2"), 2.0), (Set(Person("bar3"), Person("bar4")), 3.2)))
		result should contain(Combinations((Person("bar1"), 2.0), (Person("bar3"), 1.8), (Set(Person("bar4"), Person("bar2")), 1.5)))
		result should contain(Combinations((Person("bar1"), 2.0), (Person("bar4"), 1.3), (Set(Person("bar3"), Person("bar2")), 2.3)))
		result should contain(Combinations((Person("bar2"), 1.5), (Person("bar3"), 1.8), (Set(Person("bar4"), Person("bar1")), 2.0)))
		result should contain(Combinations((Person("bar2"), 1.5), (Person("bar4"), 1.3), (Set(Person("bar3"), Person("bar1")), 2.8)))
		result should contain(Combinations((Person("bar2"), 1.5), (Person("bar1"), 0.8), (Set(Person("bar3"), Person("bar4")), 3.2)))
		result should contain(Combinations((Person("bar3"), 1.0), (Person("bar2"), 2.0), (Set(Person("bar4"), Person("bar1")), 2.0)))
		result should contain(Combinations((Person("bar3"), 1.0), (Person("bar4"), 1.3), (Set(Person("bar1"), Person("bar2")), 1.1)))
		result should contain(Combinations((Person("bar3"), 1.0), (Person("bar1"), 0.8), (Set(Person("bar4"), Person("bar2")), 1.5)))
		result should contain(Combinations((Person("bar4"), 0.5), (Person("bar2"), 2.0), (Set(Person("bar3"), Person("bar1")), 2.8)))
		result should contain(Combinations((Person("bar4"), 0.5), (Person("bar3"), 1.8), (Set(Person("bar2"), Person("bar1")), 1.1)))
		result should contain(Combinations((Person("bar4"), 0.5), (Person("bar1"), 0.8), (Set(Person("bar3"), Person("bar2")), 2.3)))
	}
	
	test("make list"){
		val chairman = Person("bar1")
		val secretary = Person("bar2")
		val pf:PartialFunction[(Person, Double), (Person, Double)] = {
			case p if p._1 != chairman && p._1 != secretary => p
		}
		val snacks = List((Person("bar3"), 2.0), (Person("bar4"), 1.2), (Person("bar1"), 0.8), (Person("bar2"), 0.3))
		
		val result = Combinations.makeList(List.empty[(Person, Double)], snacks, pf)
		result should be (List((Person("bar4"), 1.2), (Person("bar3"), 2.0)))
	}
	
	test("toVector"){
		val combination = Combinations((Person("bar1"), 2.002), (Person("bar2"), 2.001), (Set(Person("bar3"), Person("bar4")), 3.204))
		val expected = new java.util.Vector[String]()
		expected.add("1")
		expected.add("7.21")
		expected.add("bar1")
		expected.add("bar2")
		expected.add("bar3, bar4")
		
		combination.toVector(1) should be (expected)
	}
}