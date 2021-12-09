package Operations;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateInsertQuery(String input){
        String[] tokens = input.split(" ");
        if(tokens[1].equalsIgnoreCase("into") && tokens[4].equalsIgnoreCase("values")){
            return true;
        }else {
            return false;
        }
    }

    public static List<String> getColumnValues(String input){
        List<String> column_values = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(input);

        int count = 0;
        while (matcher.find()){
            if(count==1)
                for(String s: matcher.group(1).split(","))
                    column_values.add(s);
            count++;

        }
        return column_values;
    }

    public static List<String> getColumnNames(String input) {
        List<String> column_name = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(input);
        while (matcher.find()) {
            for(String s: matcher.group(1).split(",")){
                column_name.add(s);
            }
            break;
        }
        return column_name;
    }

    public static boolean validateSelectQuery(String input) {
        String[] tokens = input.split(" ");
        if(tokens[2].equalsIgnoreCase("from")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean validateDropQuery(String query) {
        if(query.split(" ")[1].equalsIgnoreCase("database") || query.split(" ")[1].equalsIgnoreCase("table")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean validateDeleteQuery(String query) {
        if(query.split(" ")[1].equalsIgnoreCase("from") && query.split(" ")[3].equalsIgnoreCase("where")){
            return true;
        }else{
            return false;
        }


    }

    public static boolean validateUpdateQuery(String query) {
        if(query.split(" ")[2].equalsIgnoreCase("set") && query.split(" ")[4].equalsIgnoreCase("where")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean validateTransactionQuery(String query){
        if((query.split(" ")[1].toLowerCase()).startsWith("transaction")){
            return true;
        }
        else
        {
            return false;
        }
    }
}











