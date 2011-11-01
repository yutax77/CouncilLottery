package com.yutax77;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ScoresTest {
	@Test
	public void testGetResult() {
		Map<Person, Double> calced = new HashMap<Person, Double>();
		calced.put(new Person("a"), 2.0);
		calced.put(new Person("b"), 2.0);
		calced.put(new Person("c"), 1.0);
		calced.put(new Person("d"), 0.0);
		
		Scores scores = Scores.createScores(calced, calced);
		NavigableMap<Double, Set<Person>> result = scores.getResult();
		
		NavigableMap<Double, Set<Person>> expected = new TreeMap<Double, Set<Person>>();
		expected.put(4.0, new HashSet<Person>(Arrays.asList(new Person("a"), new Person("b"))));
		expected.put(2.0, new HashSet<Person>(Arrays.asList(new Person("c"))));
		expected.put(0.0, new HashSet<Person>(Arrays.asList(new Person("d"))));
		assertEquals(result, expected);
	}
}
