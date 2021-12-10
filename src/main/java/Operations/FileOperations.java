package Operations;

import Preferences.*;

import java.io.*;
import java.util.*;

public class FileOperations {

    public void writeToTable(String tableName, List<String> tableRow) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt", true);
            String temp = "";
            for(String data: tableRow){
                temp += data + ":";
            }
            fileWriter.write(temp.substring(0,temp.length()-1));

            fileWriter.write("\n");
//            System.out.println("1 row added to " + tableName);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
//                System.out.println(line);
                if(line.length() != 0){
                    lines.add(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<List<String>> readTable(String tableName) {
        List<List<String>> temp = new ArrayList<>();

        List<String> file_lines = FileOperations.readLines(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt");

        for(String line: file_lines){
            List<String> tempo = new ArrayList<>();

            String[] tokens = line.trim().split(":");
            for(String token: tokens){
                tempo.add(token);
            }

            temp.add(tempo);
        }

        return temp;

    }

    public void clearFile(String tableName) {
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt",false);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
