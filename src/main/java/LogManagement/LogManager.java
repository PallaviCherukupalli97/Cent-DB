package LogManagement;

import java.io.FileWriter;
import java.io.IOException;


public class LogManager {

	
	public  void queryLog(String query , String timestamp) {
						
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/assets/logs/query_log.txt", true);
			
			fw.append(timestamp+" : "+query+"\n");
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception in query logs"+ e.getMessage());
		}
	}
	
	public static void generalLog(String msg) {
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/assets/logs/general_log.txt", true);
			fw.append(msg);
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception in general logs"+ e.getMessage());
		}
		
	}
	
	public static void eventLog(String msg) {
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/assets/logs/event_log.txt", true);
			fw.append(msg);
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception in event logs"+ e.getMessage());
		}
		
	}
}
