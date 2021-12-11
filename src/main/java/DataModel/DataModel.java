package DataModel;

import Preferences.DatabaseSetting;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import LogManagement.LogManager;

public class DataModel {

    public void exportDataModel() throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter database name: ");
        String dbName = sc.nextLine();

        File allDatabase = new File(System.getProperty("user.dir") + "/assets/database/");
        List<String> list_of_databases = List.of(allDatabase.list());

        if(list_of_databases.contains(dbName)){
            String fileContent = "";
            try {

                File dataDictionary = new File(System.getProperty("user.dir") + "/assets/data_dictionary/" + dbName + "_data_dictionary.txt");
                try (BufferedReader br = new BufferedReader(new FileReader(dataDictionary))) {
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        fileContent += line + "\n";
                    }
                }
                File dataModel = new File(System.getProperty("user.dir") + "/assets/data_model/" + dbName + "_ERD.txt");
                dataModel.createNewFile();
                FileWriter fileWriter = new FileWriter(dataModel);
                fileWriter.write("Database: " + dbName);


                String[] tables = fileContent.split("\n\n");

                for(int i=1;i<tables.length;i++){
                    String table_name = tables[i].split("\n")[0].trim().split(":")[1].trim();
                    String[] temp = tables[i].split("\n");
                    List<String> column_line = new ArrayList<>();
                    for(int k=1;k<temp.length;k++){
                        column_line.add(temp[k]);
                    }

                    List<String> column_names = new ArrayList<>();
                    List<String> column_types = new ArrayList<>();
                    for(int q=0;q<column_line.size();q++){
                        column_names.add(column_line.get(q).split("\\|")[0].split(":")[1].trim());
                        column_types.add(column_line.get(q).split("\\|")[1].split(":")[1].trim());
                    }
                    //
                    fileWriter.write("\n\nTable: " + table_name);
                    fileWriter.write("\n------------------------");
                    fileWriter.write(String.format("\n|%10s| %10s|","Column","Data Type"));
                    fileWriter.write("\n------------------------");
                    for(int d=0;d<column_names.size();d++){
                        fileWriter.write(String.format("\n|%10s| %10s|",column_names.get(d),column_types.get(d)));
                    }
                    fileWriter.write("\n------------------------\n");

                }
                System.out.println("ERD exported successfully.");
                fileWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
                LogManager.crashReport(e);
            }

        }else {
            System.out.println("Database '" + dbName + "' not found.");
        }




    }
}
