import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.Arrays;


public class LogTest {

	@Test
	public void testGetLogElement() {
		Log log = new Log();
		LogElement expected = new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤"));
		
		assertEquals(expected, log.getElement(1));
		assertNull(log.getElement(2));
	}

}
