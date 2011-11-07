package samples.councilLottery

import scala.collection.mutable
import org.scalatest.junit.JUnitSuite
import org.scalatest.matchers.ShouldMatchers
import org.junit.Test
import com.yutax77.councilLottery.WorkerListReader
import org.scalatest.Spec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import com.yutax77.councilLottery.Person

@RunWith(classOf[JUnitRunner])
class WorkerListFunTest extends FunSuite with ShouldMatchers{
	test("read from file") {
		val actual = WorkerListReader.read(this.getClass().getResource("/DummyWorkerList.txt").getFile())
		val expected = mutable.Set(Person("hoge"), Person("foo"), Person("bar"))
		actual should be (expected)
	}
}
