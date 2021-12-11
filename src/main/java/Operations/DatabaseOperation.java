package Operations;

import DataDictionary.DataDictionary;
import LogManagement.LogManager;
import Preferences.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public void createTable(String tableName, List<String> column_titles) throws IOException {
        if (DatabaseSetting.SELECTED_DATABASE == null) {
            System.out.println("No database selected. Please select a database first.");
        } else {
            try {
                File table = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt");
                if (table.createNewFile()) {
                    System.out.println("Table created: " + tableName);
                    FileOperations io = new FileOperations();
                    io.writeToTable(tableName, column_titles);
                    //create data dictioary
                    DataDictionary dataDictionary = new DataDictionary();
                    dataDictionary.generateDataDictionary();

                } else {
                    System.out.println("Table already exists.");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.toString());
                LogManager.crashReport(e);
            }
        }
    }

    public void insertRow(String tableName, List<String> column_values) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
        List<String> list_of_tables = List.of(file.list());

        if (list_of_tables.contains(tableName + ".txt")) {
            FileOperations io = new FileOperations();
            io.writeToTable(tableName, column_values);
        } else {
            System.out.println("Table '" + tableName + "' does not exist in database '" + DatabaseSetting.SELECTED_DATABASE + "'.");
        }
    }

    public void selectFullTable(String tableName, String query) {
        if (DatabaseSetting.SELECTED_DATABASE == null) {
            System.out.println("No database selected.");
        } else {
            File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
            List<String> list_of_tables = List.of(file.list());
            if (list_of_tables.contains(tableName + ".txt")) {

                FileOperations io = new FileOperations();
                List<List<String>> resultSet = io.readTable(tableName);

                if (query.contains("where") || query.contains("WHERE")) {

                    String find_val = query.split(" ")[5].split("=")[1];
                    List<Integer> index_found = new ArrayList<>();
                    List<String> columns = resultSet.get(0);
                    for (int i = 1; i < resultSet.size(); i++) {
                        for (String s : resultSet.get(i)) {
                            if (s.equalsIgnoreCase(find_val)) {
                                index_found.add(i);
                            }
                        }
                        System.out.println();
                    }
                    if (index_found.size() > 0) {
                        for (String s : columns)
                            System.out.printf("%10s|", s.trim().split(" ")[0]);
                        System.out.println("\n---------------------------");
                        for (int i = 1; i < resultSet.size(); i++) {
                            if (index_found.contains(i)) {
                                for (String s : resultSet.get(i))
                                    System.out.printf("%10s|", s);
                                System.out.println();
                            }
                        }

                    } else {
                        System.out.println("No records found");
                    }

                } else {
                    List<String> columns = resultSet.get(0);
                    for (String s : columns)
                        System.out.printf("%10s|", s.trim().split(" ")[0]);
                    System.out.println("\n---------------------------");
                    for (int i = 1; i < resultSet.size(); i++) {
                        for (String s : resultSet.get(i))
                            System.out.printf("%10s|", s);
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Table '" + tableName + "' does not exist.");
            }
        }
    }

//    public void selectColumnFromTable(String tableName, String query) {
//        if (DatabaseSetting.SELECTED_DATABASE == null) {
//            System.out.println("No database selected.");
//        } else {
//            File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
//            List<String> list_of_tables = List.of(file.list());
//            if (list_of_tables.contains(tableName + ".txt")) {
//                FileOperations io = new FileOperations();
//                List<List<String>> resultSet = io.readTable(tableName);
//
//            } else {
//                System.out.println("Table '" + tableName + "' does not exist.");
//            }
//        }
//    }

    public void dropDatabase(String dbName) throws IOException {
        File database = new File(System.getProperty("user.dir") + "/assets/database/" + dbName);
        if (database.exists()) {
            try {
                FileUtils.deleteDirectory(database);
                System.out.println("Database '" + dbName + "' dropped successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                LogManager.crashReport(e);
            }
        } else {
            System.out.println("Database '" + dbName + "' not found. Please try again.");
        }
    }

    public void dropTable(String tableName) throws IOException {
        if (DatabaseSetting.SELECTED_DATABASE == null)
            System.out.println("No database selected");
        else {
            File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt");
            if (file.exists()) {
                try {
                    FileUtils.delete(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    LogManager.crashReport(e);
                }
//                file.delete();
                System.out.println("Table '" + tableName + "' dropped successfully.");
            } else
                System.out.println("Table '" + tableName + "' not found. Please try again.");
        }
    }

    public void deleteRow(String tableName, String query) throws IOException {
        if (DatabaseSetting.SELECTED_DATABASE == null)
            System.out.println("No database selected");
        else {
            File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
            List<String> list_of_tables = List.of(file.list());

            if(list_of_tables.contains(tableName + ".txt")){
                String del_value = query.split(" ")[4].trim().split("=")[1];
//                System.out.println(del_value);

                FileOperations io = new FileOperations();
                List<List<String>> resultSet = io.readTable(tableName);
//                System.out.println("ResultSet:\n" + resultSet);

                int del_index = -1;
                for(int i=0; i<resultSet.size();i++)
                    for(int j=0;j<resultSet.get(i).size();j++)
                        if(resultSet.get(i).get(j).equalsIgnoreCase(del_value))
                            del_index = i;
                resultSet.remove(del_index);

                io.clearFile(tableName);
                for(List<String> stt: resultSet)
                    io.writeToTable(tableName, stt);
            }else {
                System.out.println("Table '" + tableName + "' does not exist. Please try again.");
            }
        }
    }

    public void updateTable(String tableName, String query) throws IOException {
        if (DatabaseSetting.SELECTED_DATABASE == null)
            System.out.println("No database selected");
        else {
            File file = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/");
            List<String> list_of_tables = List.of(file.list());

            if(list_of_tables.contains(tableName + ".txt")){
                String update_value = query.split(" ")[3].trim().split("=")[1];
                String update_condition = query.split(" ")[5].trim().split("=")[1];
                String source_column = query.split(" ")[3].trim().split("=")[0];

                FileOperations io = new FileOperations();
                List<List<String>> resultSet = io.readTable(tableName);

                int source_column_index = -1;
                for(int i=0;i<resultSet.get(0).size();i++)
                    if(resultSet.get(0).get(i).trim().split(" ")[0].equalsIgnoreCase(source_column))
                        source_column_index = i;

                int update_index = -1;
                for(int i=0; i<resultSet.size();i++)
                    for(int j=0;j<resultSet.get(i).size();j++)
                        if(resultSet.get(i).get(j).equalsIgnoreCase(update_condition)){
                            update_index = i;
                            break;
                        }

                resultSet.get(update_index).set(source_column_index,update_value);
                resultSet.set(update_index,resultSet.get(update_index));

                io.clearFile(tableName);
                for(List<String> lines: resultSet)
                    io.writeToTable(tableName, lines);
                System.out.println("1 row updated");

            }else {
                System.out.println("Table '" + tableName + "' does not exist. Please try again.");
            }
        }
    }
}