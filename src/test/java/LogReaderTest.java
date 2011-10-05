import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class LogReaderTest {
	@Test
	public void testRead() throws IOException {
		LogReader reader = new LogReader(this.getClass().getResource("/DummyLog.json").getFile());
		Log log = reader.read();
		
		assertNotNull(log);
		assertEquals(new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤")), log.getElement(1));
		assertNull(log.getElement(2));
	}
}
