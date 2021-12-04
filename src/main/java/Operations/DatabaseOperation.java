package Operations;

import Preferences.DatabaseSetting;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.List;

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

    public void createTable(String tableName) {
        if (DatabaseSetting.SELECTED_DATABASE == null) {
            System.out.println("No database selected. Please select a database first.");
        } else {
            try {

                File table = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" +  tableName + ".txt");
                if (table.createNewFile()) {
                    System.out.println("Table created: " + tableName);
                } else {
                    System.out.println("File already exists.");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.toString());
            }
        }

    }

    public void selectTable() {

    }
}
