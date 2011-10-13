import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Log {
	private List<LogElement> elements = new ArrayList<LogElement>();
	
	public Log() {
		elements.add(new LogElement("川代", "佐々木", Arrays.asList("野村", "福澤")));
	}
	
	public LogElement getElement(int number) {
		if(number < 0 || elements.size() < number) {
			return null;
		}
		return elements.get(number - 1);
	}
	
	public Map<Person, ExpCount> calcExpCount(Set<Person> workers) {
		Map<Person, ExpCount> result = new HashMap<Person, ExpCount>();
		for(Person person: workers) {
			result.put(person, new ExpCount(0, 0));
		}
		
		int no = 1;
		for(LogElement element: elements) {
			element.addChairmanCount(result, no);
			no++;
		}
		
		return result;
	}
}
