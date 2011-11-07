package samples.councilLottery
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.codahale.jerkson.Json._
import com.yutax77.councilLottery._

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
}