package Dump;

import java.io.FileWriter;

public class DumpManager {
    public static void exportDump(String query) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/dumps/dump.sql", true);
            fileWriter.append(query + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }
}
