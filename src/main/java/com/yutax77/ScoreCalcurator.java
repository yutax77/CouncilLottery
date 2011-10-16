package com.yutax77;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ScoreCalcurator {
	private Set<Person> persons;
	private int newestNo;
	
	public ScoreCalcurator(Set<Person> persons, int newestNo) {
		this.persons = persons;
		this.newestNo = newestNo;
	}
	
	public Scores calc(Log log) {
		Map<Person, ExpCount> counts = log.calcExpCount(persons);
		Map<Person, Double> normalized = calcNormalizedCount(counts);
		Map<Person, Double> score = calcScore(normalized);
		
		Map<Person, Double> elapsed = calcElapsedTimeScore(counts, newestNo);
		return Scores.createScores(score, elapsed);
	}

	Map<Person, Double> calcNormalizedCount(Map<Person, ExpCount> counts) {
		ExpCount max = Collections.max(counts.values());
		Map<Person, Double> result = new HashMap<Person, Double>();
		
		for(Entry<Person, ExpCount> entry: counts.entrySet()) {
			result.put(entry.getKey(), ((double)entry.getValue().getCount() / max.getCount()));
		}
		
		return result;
	}

	Map<Person, Double> calcScore(Map<Person, Double> normalizedCount) {
		Map<Person, Double> result = new HashMap<Person, Double>();
		
		for(Entry<Person, Double> entry: normalizedCount.entrySet()) {
			result.put(entry.getKey(), (1 / (1 + entry.getValue())));
		}
		return result;
	}

	Map<Person, Double> calcElapsedTimeScore(Map<Person, ExpCount> counts, int newestNo) {
		Map<Person, Double> result = new HashMap<Person, Double>();
		
		for(Entry<Person, ExpCount> entry: counts.entrySet()) {
			result.put(entry.getKey(), (1.0 - (double)entry.getValue().getLastExpNo() / newestNo));
		}
		
		return result;
	}

	Scores createScores(Map<Person, Double> countScores, Map<Person, Double> elapsedScores) {
		return null;
	}

}
