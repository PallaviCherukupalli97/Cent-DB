package QueryParser;

import Operations.DatabaseOperation;

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
                    databaseOperation.createTable(name);

//                    check if database is selected
//                    call create database method
                }

                break;

            case "USE":
                String database_name = query.split(" ")[1];
                databaseOperation.useDatabase(database_name);


                break;
            case "INSERT":

                break;
            case "UPDATE":

                break;
            default:
                System.out.println("Invalid query syntax");
                break;
        }

    }


}
