package com.yutax77;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.arnx.jsonic.JSON;

public class Log {
	private List<LogElement> elements;
	
	public Log() {}
	
	public static Log createFromFile(File file) throws IOException {
		BufferedReader reader = null;
		
		Log log = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			@SuppressWarnings({ "unchecked", "serial" })
			List<LogElement> elements = (List<LogElement>) JSON.decode(reader, (new ArrayList<LogElement>() {}).getClass().getGenericSuperclass());
			log = new Log();
			log.setElements(elements);
		}
		finally {
			if(reader != null)
				reader.close();
		}
		
		return log;
	}
	
	public List<LogElement> getElements() {
		return elements;
	}

	public void setElements(List<LogElement> elements) {
		this.elements = elements;
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
