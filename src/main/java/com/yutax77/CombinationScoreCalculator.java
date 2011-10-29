package com.yutax77;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;

import com.yutax77.Combination.CombinationBuilder;

public class CombinationScoreCalculator {
	private static final int COMBINATION_NUM = 3;
	private static final Person[] DUMMY = new Person[]{};
	
	public static NavigableSet<Combination> calc(Map<TitleType, Scores> scoreResult) {
		NavigableSet<Combination> results = new TreeSet<Combination>();
		
		TreeMap<Double, Set<Person>> chairmanScores = scoreResult.get(TitleType.CHAIRMAN).getResult();
		Double key = chairmanScores.lastKey();
		TreeMap<Double, Set<Person>> secretaryScores = scoreResult.get(TitleType.SECRETARY).getResult();
		TreeMap<Double, Set<Person>> snackScores = scoreResult.get(TitleType.SNACK).getResult();
		for(int i = 0; i < COMBINATION_NUM; i++) {
			Set<Person> persons = chairmanScores.get(key);
			
			for(Person person: persons) {
				NavigableSet<Double> secretaryScoreRanks = secretaryScores.descendingKeySet();
				for(Double secretaryScore : secretaryScoreRanks) {
					NavigableMap<Double, Set<Person>> candidateSec = getCanditateSecretary(secretaryScores, person);
					for(Entry<Double, Set<Person>> entry : candidateSec.entrySet()) {
						for(Person secretary : entry.getValue()) {
							NavigableMap<Double, Set<Pair<Person, Person>>> candidateSnack = getCandidateSnack(snackScores, person, secretary, null);
							for(Entry<Double, Set<Pair<Person, Person>>> snackEntry : candidateSnack.entrySet()) {
								for(Pair<Person, Person> snackPair : snackEntry.getValue()) {
									CombinationBuilder builder = new CombinationBuilder();
									builder.chairman(key, person).secretary(secretaryScore, secretary);
									builder.snack(snackEntry.getKey(), snackPair);
									results.add(builder.build());
								}
							}
						}
					}
				}
			}
		}
		
		return results;
	}
	
	static NavigableMap<Double, Set<Person>> getCanditateSecretary(NavigableMap<Double, Set<Person>> secretaryScores, Person chairman) {
		NavigableMap<Double, Set<Person>> results = new TreeMap<Double, Set<Person>>();
		
		NavigableSet<Double> secretaryScoreRanks = secretaryScores.descendingKeySet();
		int i = 0;
		for(Double secretaryScore : secretaryScoreRanks) {
			Set<Person> secretaries = secretaryScores.get(secretaryScore);
			assert !secretaries.isEmpty();
			
			if(secretaries.contains(chairman)) {
				if(secretaries.size() == 1)
					continue;
				
				Set<Person> persons = new HashSet<Person>(secretaries);
				persons.remove(chairman);
				results.put(secretaryScore, persons);
				i++;
			}
			else {
				results.put(secretaryScore, secretaries);
				i++;
			}
			
			if(COMBINATION_NUM <= i)
				break;
		}
		
		return results;
	}
	
	static NavigableMap<Double, Set<Pair<Person, Person>>> getCandidateSnack(NavigableMap<Double, Set<Person>> snackScores,
																				Person chairman,
																				Person secretary,
																				Pair<Double, Person> keep) {
		NavigableMap<Double, Set<Pair<Person, Person>>> results = new TreeMap<Double, Set<Pair<Person, Person>>>();
		
		Set<Person> exists = new HashSet<Person>(Arrays.asList(chairman, secretary));
		for(Entry<Double, Set<Person>> snackEntry : snackScores.entrySet()) {
			Set<Person> snackes = snackEntry.getValue();
			assert !snackes.isEmpty();
			
			Set<Person> persons = new HashSet<Person>(snackes);
			if(snackes.contains(chairman) || snackes.contains(secretary)) {
				persons.removeAll(exists);
			}
			
			if(persons.size() == 0) {
				continue;
			}

			if(keep == null) {
				if(persons.size() == 1) {
					Person[] array = persons.toArray(DUMMY);
					
					results.putAll(getCandidateSnack(snackScores.headMap(snackEntry.getKey(), false),
							chairman, secretary, Pair.of(snackEntry.getKey(), array[0])));
				}
				else {
					double totalScore = snackEntry.getKey() * 2;
					results.put(totalScore, makePair(persons));
				}				
			}
			else {
				double totalScore = keep.getKey() + snackEntry.getKey();
				Set<Pair<Person, Person>> pairs = new HashSet<Pair<Person, Person>>();
					for(Person person : persons) {
						pairs.add(Pair.of(keep.getValue(), person));
				}
				results.put(totalScore, pairs);
			}
		}
		
		return results;
	}
	
	static Set<Pair<Person, Person>> makePair(Set<Person> persons) {
		assert 1 < persons.size();
		
		Set<Pair<Person, Person>> results = new HashSet<Pair<Person, Person>>();
		
		Person[] array = persons.toArray(DUMMY);
		int size = array.length;
		for(int i = 0; i < size - 1; i++) {
			for(int j = i + 1; j < size; j++) {
				results.add(Pair.of(array[i], array[j]));
			}
		}
		
		return results;
	}
}
