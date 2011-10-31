package com.yutax77;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

public class CombinationScoreCalculatorTest {
  @Test
  public void testCalc() {
	  Map<TitleType, Scores> input = new EnumMap<TitleType, Scores>(TitleType.class);
	  Person a = new Person("a");
	  Person b = new Person("b");
	  Person c = new Person("c");
	  Person d = new Person("d");
	  
	  Map<Person, Double> chairmanScore = new HashMap<Person, Double>();
	  chairmanScore.put(a, 0.0);
	  chairmanScore.put(b, 1.0);
	  chairmanScore.put(c, 1.0);
	  chairmanScore.put(d, 1.0);
	  
	  Map<Person, Double> secretaryScore = new HashMap<Person, Double>();
	  secretaryScore.put(a, 1.0);
	  secretaryScore.put(b, 0.0);
	  secretaryScore.put(c, 1.0);
	  secretaryScore.put(d, 1.0);
	  
	  Map<Person, Double> snackScore = new HashMap<Person, Double>();
	  snackScore.put(a, 1.0);
	  snackScore.put(b, 1.0);
	  snackScore.put(c, 0.0);
	  snackScore.put(d, 0.0);
	  
	  input.put(TitleType.CHAIRMAN, Scores.createScores(chairmanScore, chairmanScore));
	  input.put(TitleType.SECRETARY, Scores.createScores(secretaryScore, secretaryScore));
	  input.put(TitleType.SNACK, Scores.createScores(snackScore, snackScore));
	  
	  NavigableSet<Combination> result = CombinationScoreCalculator.calc(input);
	  
	  assertNotNull(result);
  }
  
  @Test
  public void testMakePair() {
	  Person a = new Person("a");
	  Person b = new Person("b");
	  Person c = new Person("c");
	  
	  Set<Person> input = new TreeSet<Person>(Arrays.asList(a, b, c));
	  
	  Set<Pair<Person, Person>> expected = new HashSet<Pair<Person, Person>>();
	  expected.add(Pair.of(a, b));
	  expected.add(Pair.of(a, c));
	  expected.add(Pair.of(b, c));
	  
	  assertEquals(CombinationScoreCalculator.makePair(input), expected);
  }
}
