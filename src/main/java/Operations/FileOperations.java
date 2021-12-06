package Operations;

import Preferences.*;

import java.io.*;
import java.util.*;

public class FileOperations {

    public void appendHashMap(String tableName, List<String> tableRow) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt", true);
            for(String data: tableRow){
                fileWriter.write(data + ":");
            }
            fileWriter.write("\n");
            System.out.println("1 row added to " + tableName);
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




//        String[] entries = file_lines.get(0).trim().split(":");
//        for (String each_entry : entries) {
//            keys.add(each_entry.trim().split(" ")[0]);
//        }
//        System.out.println("Keys: " + keys);
//        System.out.println("Size: " + file_lines.size());

//        int index = 0;
//        for(String line: file_lines){
//            List<String> tempo = new ArrayList<>();
//
//            String[] temp = line.trim().split(":");
//
//            for(String s: temp){
//                tempo.add(s.trim().split(" ")[1]);
//            }
//            m1.put(keys.get(index), tempo);
//            index++;
//        }
//
//        return m1;

//        System.out.println("Keys: " + keys);
//
//
//        int cc = 0;
//        for (String line : file_lines) {
//            List<String> values = new ArrayList<>();
//
//            entries = line.trim().split(":");
//            for (String each_entry : entries) {
////                System.out.println(each_entry);
//                values.add(each_entry.trim().split(" ")[1]);
//            }
//            m1.put(keys.get(cc++), values);
//        }


//        try {
//            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt"));
//            String line = null;
////            List<String> keys = new ArrayList<>();
//            while ((line = br.readLine()) != null) {
////                System.out.println(line);
//                // line = "name 'prit' : id '26' : age '23' :"
//                String[] entries = line.trim().split(":");
//                for (String each_entry : entries) {
//                    //each_entry = "name 'prit'"
//                    keys.add(each_entry.trim().split(" ")[0]);
//
//                }
//                System.out.print("Keys: ");
//                for (String a : keys) {
//                    System.out.print(a + ", ");
//                }
//                break;
//            }
//
//        int cc = 0;
//            BufferedReader br1 = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt"));


//            while ((line = br1.readLine()) != null) {
//                List<String> values = new ArrayList<>();
////                System.out.println(line);
//                // line = "name 'prit' : id '26' : age '23' :"
//
////                System.out.println("Line: " + line);
//                String[] entries = line.trim().split(":");
////                System.out.println("Entries: " + Arrays.toString(entries));
//
//                for (String each_entry : entries) {
//                    //each_entry = "name 'prit'"
//                    values.add(each_entry.trim().split(" ")[1]);
//                }
////                System.out.println(values);
//                m1.put(keys.get(cc++), values);
//            }


//        } catch (Exception e) {
//            System.out.println("Exception occured: " + e.toString());
//        }
//        return m1;
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
