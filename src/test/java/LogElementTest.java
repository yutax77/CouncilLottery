import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LogElementTest {

  @Test
  public void testAddChairmanCount() {
	  LogElement element = new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤"));
	  Map<Person, ExpCount> result = new HashMap<Person, ExpCount>();
	  element.addChairmanCount(result, 1);
	  
	  Map<Person, ExpCount> expected = new HashMap<Person, ExpCount>();
	  expected.put(new Person("川代"), new ExpCount(1, 1));
	  assertEquals(result, expected);
  }
}
