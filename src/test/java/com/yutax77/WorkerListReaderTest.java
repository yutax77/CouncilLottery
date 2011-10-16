package com.yutax77;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.io.IOException;
import java.util.Set;
public class WorkerListReaderTest {
	@Test
	public void testRead() throws IOException {
		WorkerListReader reader = new WorkerListReader(this.getClass().getResource("/DummyWorkerList.txt").getFile());
		Set<Person> persons = reader.read();
		
		assertTrue(persons.contains(new Person("川代")));
		assertTrue(persons.contains(new Person("野村")));
		assertFalse(persons.contains(new Person("伊藤")));
	}
}