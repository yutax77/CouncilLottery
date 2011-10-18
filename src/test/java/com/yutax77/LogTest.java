package com.yutax77;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;


public class LogTest {
	private File dummyLog = new File(this.getClass().getResource("/DummyLog.json").getFile());
	
	@Test
	public void testGetLogElement() throws IOException {
		Log log = Log.createFromFile(dummyLog);
		LogElement expected = new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤"));
		
		assertEquals(expected, log.getElement(1));
		assertNull(log.getElement(2));
	}
	
	@Test
	public void testCalcExpCount() throws IOException {
		Log log = Log.createFromFile(dummyLog);
		Set<Person> workers = new HashSet<Person>();
		workers.addAll(Arrays.asList(new Person("川代"), new Person("佐々木"), new Person("福澤"), new Person("野村")));
		Map<TitleType, Map<Person, ExpCount>> actual = log.calcExpCount(workers);
		
		Map<Person, ExpCount> chairmanExpected = new HashMap<Person, ExpCount>();
		chairmanExpected.put(new Person("川代"), new ExpCount(1, 1));
		chairmanExpected.put(new Person("佐々木"), new ExpCount(0, 0));
		chairmanExpected.put(new Person("福澤"), new ExpCount(0, 0));
		chairmanExpected.put(new Person("野村"), new ExpCount(0, 0));
		
		Map<Person, ExpCount> secretaryExpected = new HashMap<Person, ExpCount>();
		secretaryExpected.put(new Person("川代"), new ExpCount(0, 0));
		secretaryExpected.put(new Person("佐々木"), new ExpCount(1, 1));
		secretaryExpected.put(new Person("福澤"), new ExpCount(0, 0));
		secretaryExpected.put(new Person("野村"), new ExpCount(0, 0));
		
		Map<Person, ExpCount> snackExpected = new HashMap<Person, ExpCount>();
		snackExpected.put(new Person("川代"), new ExpCount(0, 0));
		snackExpected.put(new Person("佐々木"), new ExpCount(0, 0));
		snackExpected.put(new Person("福澤"), new ExpCount(1, 1));
		snackExpected.put(new Person("野村"), new ExpCount(1, 1));
		
		Map<TitleType, Map<Person, ExpCount>> expected = new EnumMap<TitleType, Map<Person, ExpCount>>(TitleType.class);
		expected.put(TitleType.CHAIRMAN, chairmanExpected);
		expected.put(TitleType.SECRETARY, secretaryExpected);
		expected.put(TitleType.SNACK, snackExpected);
		assertEquals(actual, expected);
	}
	
	@Test
	public void testCreateFromFile() throws IOException {
		Log log = Log.createFromFile(dummyLog);
		
		assertNotNull(log);
		assertEquals(new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤")), log.getElement(1));
		assertNull(log.getElement(2));
	}
}
