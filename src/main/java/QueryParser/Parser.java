package QueryParser;

import Operations.DatabaseOperation;
import Operations.RegexOperations;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public String takeInput(){
        System.out.println("Enter a query here: ");
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        return temp;
    }

    public boolean validQuery(String query) {
        if(query.charAt(query.length() - 1) == ';'){
            return true;
        }else {
            System.out.println("Query is invalid");
            return false;
        }
    }

    public void executeQuery(String query){
        query = query.substring(0, query.length()-1);
//        System.out.println("Executing query");
        String task = query.split(" ")[0];

        DatabaseOperation databaseOperation = new DatabaseOperation();

        switch (task.toUpperCase()){
            case "CREATE":

                String keyword = query.split(" ")[1];
                String name = query.split(" ")[2];
                if(keyword.equalsIgnoreCase("database")){
                    databaseOperation.createDatabase(name);
//                    call create table method
                }else if(keyword.equalsIgnoreCase("table")){
                    List<String> column_data = RegexOperations.getColumnValues(query);
                    databaseOperation.createTable(name, column_data);

//                    check if database is selected
//                    call create database method
                }

                break;

            case "USE":
                String database_name = query.split(" ")[1];
                databaseOperation.useDatabase(database_name);


                break;
            case "INSERT":
                if(RegexOperations.validInsertQuery(query)){
                    List<String> column_values = RegexOperations.getColumnValues(query);
                    List<String> column_names = RegexOperations.getColumnNames(query);
                    String tableName = query.split(" ")[2];

                    databaseOperation.insertRow(tableName,column_names,column_values);

//                    System.out.println("Table name: " + tableName);
//                    System.out.println("Column names: " + Arrays.toString(column_names.toArray()));
//                    System.out.println("Column values: " + Arrays.toString(column_values.toArray()));



//                    databaseOperation.insertRow(tableName, column_names, values);

                }else{
                    System.out.println("Invalid query. Please try again.");
                }

                break;
            case "UPDATE":

                break;
            default:
                System.out.println("Invalid query syntax");
                break;
        }

    }


}
