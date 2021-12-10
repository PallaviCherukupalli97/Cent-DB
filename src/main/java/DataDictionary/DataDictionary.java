package DataDictionary;


import Preferences.DatabaseSetting;

import java.io.*;
import java.util.*;


public class DataDictionary {


    public void generateDataDictionary() throws IOException {
        File dataDictionary = new File(System.getProperty("user.dir") + "/assets/data_dictionary/" + DatabaseSetting.SELECTED_DATABASE + "_data_dictionary.txt");

        dataDictionary.createNewFile();

        File tables = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
        List<String> listOfTables = List.of(tables.list());

        FileWriter fileWriter = new FileWriter(dataDictionary, false);
        fileWriter.write("Database: " + DatabaseSetting.SELECTED_DATABASE);

        for (String table : listOfTables) {

            fileWriter.write("\n\nTable name: " + table.substring(0,table.length()-4));

            String firstLine = readFirstLineOfTable(table);
            for(String column: firstLine.trim().split(":")){
                fileWriter.write("\nColumn name: " + column.trim().split(" ")[0]);
                fileWriter.write("\t|Column type: " + column.trim().split(" ")[1]);
            }
        }
        fileWriter.write("\n");
        fileWriter.close();

    }

    private String readFirstLineOfTable(String table) {
        String firstLine = "";
        try{
            File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + table);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((firstLine = br.readLine()) != null) {
                return firstLine;
            }
        }catch (Exception e){
            System.out.println("Exception occurred: " + e.toString());
        }
        return firstLine;
    }
}


