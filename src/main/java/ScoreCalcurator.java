import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ScoreCalcurator {
	
	public Scores calc(Log log) {
		Map<Person, ExpCount> counts = log.calcExpCount(workers)
		return new Scores();
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



}
