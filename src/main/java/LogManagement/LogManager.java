package LogManagement;

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
            file.append(getCurrentTimeStamp() + " : " + query + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    public static void generalLog(String query) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/logs/general_log.txt", true);
            fileWriter.append(getCurrentTimeStamp() + " : " + query);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    public void eventLog(String query) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/logs/event_log.txt", true);
            fileWriter.append(query);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }
}
