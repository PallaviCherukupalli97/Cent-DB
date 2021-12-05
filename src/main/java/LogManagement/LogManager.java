package LogManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {

	public static void main(String[] args) {
		queryLog("Started","ts");
		// test code
		try {
			int x=10;
			int y =0;
			int z= x/y;
		}
		catch(Exception e) {
			queryLog(e.toString(),"ts");
		}
	}
	public static void queryLog(String query , String timest) {
						
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/assets/logs/query_log.txt", true);
			LocalDateTime currentDT = LocalDateTime.now();
			String timestamp = currentDT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
			
			fw.append(timestamp+" : "+query+"	"+"Timestamp of the query :"+"	"+timest+"\n");
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
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception in event logs"+ e.getMessage());
		}
		
	}
}
