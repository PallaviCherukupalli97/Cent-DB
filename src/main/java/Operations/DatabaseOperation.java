package Operations;

import Preferences.DatabaseSetting;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseOperation {
    public void createDatabase(String dbName) {
        boolean ret = new File(System.getProperty("user.dir") + "/assets/database/" + dbName).mkdirs();
        System.out.println("Database " + dbName + " created successfully");

    }

    public void useDatabase(String dbName) {
        File database = new File(System.getProperty("user.dir") + "/assets/database/");
        List<String> list_of_databases = List.of(database.list());
        if (list_of_databases.contains(dbName)) {
            DatabaseSetting.SELECTED_DATABASE = dbName;
            System.out.println("Database '" + DatabaseSetting.SELECTED_DATABASE + "' selected.");
        } else {
            System.out.println("Database '" + dbName + "' not found. Please try again");
        }

    }

    public void createTable(String tableName, List<String> column_values) {
        if (DatabaseSetting.SELECTED_DATABASE == null) {
            System.out.println("No database selected. Please select a database first.");
        } else {
            try {
//                System.out.println("Table name: " + tableName);
//                System.out.println("Values: " + Arrays.toString(column_values.toArray()));

                //TODO
                // Store tableName and column_values into data dictionary as follows
                // tableName = "Person", column_values = List of String where each string is: "column_name datatype"
                // <Table name> <column1_name> <column1_datatype> <column2_name> <column2_datatype>

                File table = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" +  tableName + ".txt");
                if (table.createNewFile()) {
                    System.out.println("Table created: " + tableName);
                } else {
                    System.out.println("Table already exists.");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.toString());
            }
        }

    }
    public void insertRow(String tableName, List<String> column_names, List<String> column_values){

        File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
        List<String> list_of_tables = List.of(file.list());
//        System.out.println(list_of_tables);

        if(list_of_tables.contains(tableName + ".txt")){
            Map<String, String> tableRow = new HashMap<>();
            for(int i=0;i<column_names.size();i++){
                tableRow.put(column_names.get(i), column_values.get(i));
            }
            FileOperations io = new FileOperations();
            io.appendHashMap(tableName, tableRow);

//            tableRow.put()

        }else {
            System.out.println("Table '" + tableName + "' does not exist in database '" + DatabaseSetting.SELECTED_DATABASE + "'.");
        }


        //check if table exists

    }

}
