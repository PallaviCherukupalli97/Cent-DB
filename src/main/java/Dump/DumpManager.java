package Dump;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DumpManager {
	
    public static void exportDump() throws IOException {
        	
    	FileReader fr = new FileReader(System.getProperty("user.dir") + "/assets/dumps/dumpquery.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String str;
		while((str =br.readLine()) != null) 
		{
			FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/dumps/dump.sql", true);
            fileWriter.append(str + "\n");
			fileWriter.close();
		}
		br.close();
    }
       
    public static void dump(String query) {
    	try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/assets/dumps/dumpquery.txt", true);
            file.append(query + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }
       
}
