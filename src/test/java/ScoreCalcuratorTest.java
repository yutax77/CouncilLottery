import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ScoreCalcuratorTest {
	@Test
	public void testCalcChairmanScore() {
		Log log = new Log();
		ScoreCalcurator calc = new ScoreCalcurator();
		Scores scores = calc.calc(log);
		
		assertNotNull(scores);
	}

	@Test
	public void testCalcNormalizedCount() {
		Log log = new Log();
		ScoreCalcurator calc = new ScoreCalcurator();
		
		Map<Person, ExpCount> counts = new HashMap<Person, ExpCount>();
		counts.put(new Person("川代"), new ExpCount(3, 0));
		counts.put(new Person("佐々木"), new ExpCount(2, 0));
		counts.put(new Person("福澤"), new ExpCount(1, 0));
		counts.put(new Person("野村"), new ExpCount(0, 0));
		Map<Person, Double> actual = calc.calcNormalizedCount(counts);
		
		Map<Person, Double> expected = new HashMap<Person, Double>();
		expected.put(new Person("川代"), 1.0);
		expected.put(new Person("佐々木"), 0.6666666666666666);
		expected.put(new Person("福澤"), 0.33333333333333333);
		expected.put(new Person("野村"), 0.0);
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testCalcScore() {
		ScoreCalcurator calc = new ScoreCalcurator();
		
		Map<Person, Double> normalizedCount = new HashMap<Person, Double>();
		normalizedCount.put(new Person("川代"), 1.0);
		normalizedCount.put(new Person("佐々木"), 0.6666666666666666);
		normalizedCount.put(new Person("福澤"), 0.3333333333333333);
		normalizedCount.put(new Person("野村"), 0.0);
		Map<Person, Double> actual = calc.calcScore(normalizedCount);
		
		Map<Person, Double> expected = new HashMap<Person, Double>();
		expected.put(new Person("川代"), 0.5);
		expected.put(new Person("佐々木"), 0.6000000000000001);
		expected.put(new Person("福澤"), 0.75);
		expected.put(new Person("野村"), 1.0);
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testCalcElapsedTimeScore() {
		ScoreCalcurator calc = new ScoreCalcurator();
		
		Map<Person, ExpCount> counts = new HashMap<Person, ExpCount>();
		counts.put(new Person("川代"), new ExpCount(1, 3));
		counts.put(new Person("佐々木"), new ExpCount(0, 2));
		counts.put(new Person("福澤"), new ExpCount(0, 1));
		counts.put(new Person("野村"), new ExpCount(0, 0));
		Map<Person, Double> actual = calc.calcElapsedTimeScore(counts, 3);
		
		Map<Person, Double> expected = new HashMap<Person, Double>();
		expected.put(new Person("川代"), 0.0);
		expected.put(new Person("佐々木"), 0.33333333333333337);
		expected.put(new Person("福澤"), 0.6666666666666667);
		expected.put(new Person("野村"), 1.0);
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testCreateScores() {
		ScoreCalcurator calc = new ScoreCalcurator();
		
		Map<Person, Double> scores1 = new HashMap<Person, Double>();
		scores1.put(new Person("川代"), 0.0);
		scores1.put(new Person("佐々木"), 2.0);
		scores1.put(new Person("福澤"), 3.0);
		scores1.put(new Person("野村"), 1.0);
		
		Map<Person, Double> scores2 = new HashMap<Person, Double>();
		scores2.put(new Person("川代"), 0.0);
		scores2.put(new Person("佐々木"), 2.0);
		scores2.put(new Person("福澤"), 3.0);
		scores2.put(new Person("野村"), 1.0);
		
		Scores actual = calc.createScores(scores1, scores2);
		assertNull(actual);
	}
}
