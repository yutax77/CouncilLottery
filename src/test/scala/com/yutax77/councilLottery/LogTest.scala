package com.yutax77.councilLottery
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.codahale.jerkson.Json._
import com.yutax77.councilLottery._
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class LogTest extends FunSuite with ShouldMatchers{
	test("read from string") {
		val actual = parse[List[LogElement]]("""[
			{
				"chairman" : "hoge",
				"secretary" : "foo",
				"snack" : ["bar1", "bar2"]
			}
	
		]""")
		val expected = List(LogElement("hoge", "foo", Set("bar1", "bar2")))
		actual should be (expected)
	}
	
	test("create Log from string") {
		val list = parse[List[LogElement]]("""[
			{
				"chairman" : "hoge",
				"secretary" : "foo",
				"snack" : ["bar1", "bar2"]
			}
	
		]""")
		val actual = Log.create(list, Set.empty[Person])
		val expected = new Log(List(Person("hoge")), List(Person("foo")), List(Set(Person("bar1"), Person("bar2"))))
		actual should be (expected)
	}	
	
	test("create Log from file") {
		val source = Source.fromFile(this.getClass().getResource("/DummyLog.json").getFile())
		val list = parse[List[LogElement]](source)
		val actual = Log.create(list, Set.empty[Person])
		val expected = new Log(List(Person("hoge")), List(Person("foo")), List(Set(Person("bar1"), Person("bar2"))))
		actual should be (expected)
		source.close
	}	
}