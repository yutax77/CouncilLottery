import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;


public class LogTest {

	@Test
	public void testGetLogElement() {
		Log log = new Log();
		LogElement expected = new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤"));
		
		assertEquals(expected, log.getElement(1));
		assertNull(log.getElement(2));
	}
	
	@Test
	public void testCalcExpCount() {
		Log log = new Log();
		Set<Person> workers = new HashSet<Person>();
		workers.addAll(Arrays.asList(new Person("川代"), new Person("佐々木"), new Person("福澤"), new Person("野村")));
		Map<Person, ExpCount> actual = log.calcExpCount(workers);
		
		Map<Person, ExpCount> expected = new HashMap<Person, ExpCount>();
		expected.put(new Person("川代"), new ExpCount(1, 1));
		expected.put(new Person("佐々木"), new ExpCount(0, 0));
		expected.put(new Person("福澤"), new ExpCount(0, 0));
		expected.put(new Person("野村"), new ExpCount(0, 0));
		assertEquals(actual, expected);
	}
}
