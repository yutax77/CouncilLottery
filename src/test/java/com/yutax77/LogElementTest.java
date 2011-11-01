package com.yutax77;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class LogElementTest {
	@Test
	public void testAddChairmanCount() {
		LogElement element = new LogElement("川代", "佐々木", Arrays.asList("野村",
				"福澤"));
		Map<Person, ExpCount> result = new HashMap<Person, ExpCount>();
		result.put(new Person("川代"), new ExpCount(0, 0));
		element.addChairmanCount(result, 1);

		Map<Person, ExpCount> expected = new HashMap<Person, ExpCount>();
		expected.put(new Person("川代"), new ExpCount(1, 1));
		assertEquals(result, expected);
	}

	@Test
	public void testAddSecretaryCount() {
		LogElement element = new LogElement("川代", "佐々木", Arrays.asList("野村",
				"福澤"));
		Map<Person, ExpCount> result = new HashMap<Person, ExpCount>();
		result.put(new Person("佐々木"), new ExpCount(0, 0));
		element.addSecretaryCount(result, 1);

		Map<Person, ExpCount> expected = new HashMap<Person, ExpCount>();
		expected.put(new Person("佐々木"), new ExpCount(1, 1));
		assertEquals(result, expected);
	}

	@Test
	public void testAddSnackCount() {
		LogElement element = new LogElement("川代", "佐々木", Arrays.asList("野村",
				"福澤"));
		Map<Person, ExpCount> result = new HashMap<Person, ExpCount>();
		result.put(new Person("野村"), new ExpCount(0, 0));
		result.put(new Person("福澤"), new ExpCount(0, 0));
		element.addSnackCount(result, 1);

		Map<Person, ExpCount> expected = new HashMap<Person, ExpCount>();
		expected.put(new Person("野村"), new ExpCount(1, 1));
		expected.put(new Person("福澤"), new ExpCount(1, 1));
		assertEquals(result, expected);
	}
}
