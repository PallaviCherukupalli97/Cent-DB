package QueryParser;

import Operations.*;

<<<<<<< HEAD
=======
import java.io.*;
import java.util.ArrayList;
>>>>>>> e7e02ae60e1c098efeca988d25b8c1b3a7e65941
import java.util.List;
import java.util.Scanner;
import Preferences.*;

public class Parser {
    List<String> column_titles;
    public String takeInput() throws IOException {
        System.out.println("Enter a query here: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public boolean validQuery(String query) {
        if (query.charAt(query.length() - 1) == ';') {
            return true;
        } else {
            System.out.println("Query is invalid");
            return false;
        }
    }

    public void executeQuery(String query) {
        query = query.substring(0, query.length() - 1);
//        System.out.println("Executing query");
        String task = query.split(" ")[0];

        DatabaseOperation databaseOperation = new DatabaseOperation();

        switch (task.toUpperCase()) {
            case "CREATE":

                String keyword = query.split(" ")[1];
                String name = query.split(" ")[2];
                if (keyword.equalsIgnoreCase("database")) {
                    databaseOperation.createDatabase(name);
//                    call create table method
                } else if (keyword.equalsIgnoreCase("table")) {
                    column_titles = Validator.getColumnNames(query);
                    System.out.println(column_titles);
                    databaseOperation.createTable(name, column_titles);

//                    check if database is selected
//                    call create database method
                }

                break;

            case "USE":
                String database_name = query.split(" ")[1];
                databaseOperation.useDatabase(database_name);


                break;


            case "SELECT":
                String token = query.split(" ")[1];
                String tableName = query.split(" ")[3];
                if (Validator.validateSelectQuery(query)) {
//                    if (token.equals("*")) {
                        // SELECT * FROM p1
                        databaseOperation.selectFullTable(tableName, query);
//                    } else {
                        // SELECT id from p1
//                        String column_name = query.split(" ")[1];
//                        databaseOperation.selectColumnFromTable(tableName, query);

//                    }

                } else {
                    System.out.println("Invalid query. Please try again");
                }


                break;
            case "INSERT":
                if (Validator.validateInsertQuery(query)) {
                    List<String> column_values = Validator.getColumnValues(query);
//                    List<String> column_names =  RegexOperations.getColumnNames(query);
                    String table = query.split(" ")[2];

                    databaseOperation.insertRow(table, column_values);

                } else {
                    System.out.println("Invalid query. Please try again.");
                }

                break;


            case "DELETE":
                if (Validator.validateDeleteQuery(query)) {
                    tableName = query.split(" ")[2];
                    databaseOperation.deleteRow(tableName, query);


                } else {
                    System.out.println("Invalid query. Please try again");
                }


                break;
            case "DROP":
                if (Validator.validateDropQuery(query)) {
                    String token1 = query.split(" ")[1];
                    if (token1.equalsIgnoreCase("database")) {
                        database_name = query.split(" ")[2];
                        databaseOperation.dropDatabase(database_name);
                    } else if (token1.equalsIgnoreCase("table")) {
                        tableName = query.split(" ")[2];
                        databaseOperation.dropTable(tableName);
                    }

                } else {
                    System.out.println("Invalid query. Please try again.");
                }


                break;

            case "UPDATE":
                if (Validator.validateUpdateQuery(query)) {
                    tableName = query.split(" ")[1];
                    databaseOperation.updateTable(tableName, query);

                } else {
                    System.out.println("Invalid query. Please try again.");
                }

                break;

            default:
                System.out.println("Invalid query syntax");
                break;
        }
    }
}