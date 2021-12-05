package Dump;
import java.io.FileWriter;
import java.io.IOException;

public class DumpManager {

	public  void exportDump(String query) {
		
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/assets/dumps/dump.sql", true);
			
			fw.append(query+"\n");
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception in dumps"+ e.getMessage());
		}
	}
}
