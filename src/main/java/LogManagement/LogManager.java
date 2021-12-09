package LogManagement;

import Preferences.DatabaseSetting;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogManager {

    private static String getCurrentTimeStamp() {
        LocalDateTime currentDT = LocalDateTime.now();
        String timestamp = currentDT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return  timestamp;
    }

    public static void queryLog(String query) {
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/assets/logs/query_log.txt", true);
            file.append(getCurrentTimeStamp() + " : " + DatabaseSetting.ACTIVE_USER + " : " + query + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    public static void generalLog(long timer) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/logs/general_log.txt", true);
            String dirPath=System.getProperty("user.dir")+"/assets/database/database1";
            File file= new File(dirPath);
            int fileCounter=0;
            String str[] = file.list();
            
            for(String s:str){
        		File fls= new File(file,s);
        		if(fls.isFile()){
        			fileCounter++;
        		}
        		
        	}
            fileWriter.append("Database contains"+" "+fileCounter+"	"+"tables at:"+" "+getCurrentTimeStamp()+"\n");
            fileWriter.append("Query execution time :"+"	"+timer+"\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    public void eventLog(String database) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/logs/event_log.txt", true);
            fileWriter.append("Database changed to : "+database+"\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }
    
    public void transactionMsg(String msg) {
    	 try {
             FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/logs/event_log.txt", true);
             fileWriter.append(msg+"\n");
             fileWriter.close();
         } catch (Exception e) {
             System.out.println("Exception occurred: " + e.toString());
         }
    }
}
