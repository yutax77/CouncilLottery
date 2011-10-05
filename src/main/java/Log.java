import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
