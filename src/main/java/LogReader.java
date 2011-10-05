import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.arnx.jsonic.JSON;

public class LogReader {
	private File file;
	
	public LogReader(String fileName) {
		file = new File(fileName);
	}

	public Log read() throws IOException {
		BufferedReader reader = null;
		
		Log log = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			log = JSON.decode(reader, Log.class);
		}
		finally {
			if(reader != null)
				reader.close();
		}
		
		return log;
	}

}
