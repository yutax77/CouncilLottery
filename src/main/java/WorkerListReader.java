import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class WorkerListReader {
	private File file;
	
	public WorkerListReader(String fileName) {
		file = new File(fileName);
	}

	public Set<Person> read() throws IOException {
		BufferedReader reader = null;
		Set<Person> result = new HashSet<Person>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()	) != null) {
				result.add(new Person(line));
			}
		}
		finally {
			if(reader != null)
				reader.close();
		}
		
		return result;
	}

}
