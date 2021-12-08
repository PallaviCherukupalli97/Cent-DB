package DataDictionary;


import Preferences.DatabaseSetting;

import java.io.*;
import java.util.*;


public class DataDictionary {

    public String takeInput() {
        System.out.println("Enter the name of the database you want metadata for: ");
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        return temp;
    }

    public boolean validateDatabase(String databaseName) throws FileNotFoundException {
        File directoryPath = new File("./assets/database");
        String databases[] = directoryPath.list();
        for (int i = 0; i < databases.length; i++) {
            if (databases[i].equals(databaseName)) {
                return true;
            }
        }
        return false;


    }

    public void generateDataDictionary(String databaseName) throws IOException {
        File dataDictionary = new File(System.getProperty("user.dir") + "/assets/data_dictionary/" + databaseName + "_data_dictionary.txt");
        dataDictionary.createNewFile();

        File tables = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
        List<String> list_of_tables = List.of(tables.list());

        FileWriter fileWriter = new FileWriter(dataDictionary);
        fileWriter.write("Database name: " + databaseName);

        for (String table : list_of_tables) {

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


