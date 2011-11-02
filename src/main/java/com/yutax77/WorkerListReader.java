package com.yutax77;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
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
