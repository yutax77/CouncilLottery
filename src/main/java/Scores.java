import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;

public class Scores {
	private TreeMap<Double, Set<Person>> results;
	
	private Scores(TreeMap<Double, Set<Person>> results) {
		this.results = results;
	}
	
	public static Scores createScores(Map<Person, Double> score, Map<Person, Double> elapsed) {
		TreeMap<Double, Set<Person>> result = new TreeMap<Double, Set<Person>>();
		
		for(Entry<Person, Double> entry : score.entrySet()) {
			Person person = entry.getKey();
			Double total = entry.getValue();
			assert total != null;
			
			Double elapsedScore = elapsed.get(person);
			if(elapsedScore != null) {
				total += elapsedScore;
			}
			
			Set<Person> persons = result.get(total);
			if(persons == null) {
				persons = new HashSet<Person>(Arrays.asList(person));
				result.put(total, persons);
			}
			else {
				persons.add(person);
			}
		}
		
		return new Scores(result);
	}
	
	public TreeMap<Double, Set<Person>> getResult() {
		return results;
	}
}
