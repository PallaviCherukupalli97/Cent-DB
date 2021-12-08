package Analytics;

import Operations.Validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class AnalyticsGenerator {

    public void countTotalQueries() throws IOException {
        String strLine;
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./assets/recordInputs/Inputs.txt"))) {
            while ((strLine = reader.readLine()) != null) {
                String lastCharacter = strLine.substring(strLine.length() - 1);
                if (lastCharacter.equals(":")) {
                    continue;
                } else {
                    lines = lines + 1;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String write1 = "Total number of queries (valid + invalid): " + lines;
        System.out.println(write1);
        File file = new File("./assets/analytics/analytics.txt");
        file.getParentFile().mkdirs();
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(write1);
        pr.close();
        br.close();
        fr.close();

    }

    public void numberOfDatabases() throws IOException {
        long count = 0;
        try (Stream<Path> files = Files.list(Paths.get("./assets/database/"))) {
            count = files.count() - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        String write2 = "Number of Databases:  " + count;
        System.out.println(write2);
        File file = new File("./assets/analytics/analytics.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(write2);
        pr.close();
        br.close();
        fr.close();
    }

    public void numberOfTables() throws IOException {

        File folder = new File("./assets/database/");
        File[] listOfFiles = folder.listFiles();
        int countTables = 0;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                String databaseName = listOfFiles[i].getName();
                File database = new File("./assets/database/" + databaseName + "/");
                File[] listOfTables = database.listFiles();
                countTables = listOfTables.length + countTables;
            }
        }
        String write3 = "Number of Tables: " + countTables;
        System.out.println(write3);
        File file = new File("./assets/analytics/analytics.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(write3);
        pr.close();
        br.close();
        fr.close();

    }

    public void numberOfTablesInEachDatabase() throws IOException {

        File folder = new File("./assets/database/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                String databaseName = listOfFiles[i].getName();
                File database = new File("./assets/database/" + databaseName + "/");
                File[] listOfTables = database.listFiles();
                String write4 = "Number of Tables in " + databaseName + ": " + listOfTables.length;
                System.out.println(write4);
                File file = new File("./assets/analytics/analytics.txt");
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(fr);
                PrintWriter pr = new PrintWriter(br);
                pr.println(write4);
                pr.close();
                br.close();
                fr.close();
            }
        }

    }


    public void numberOfValidQueries() throws IOException {
        String fileLine;
        int createDatabaseCount = 0;
        int createTableCount = 0;
        int dropDatabaseCount = 0;
        int dropTableCount = 0;
        int updateTableCount = 0;
        int insertTableCount = 0;
        int deleteTableCount = 0;
        String write6 = null;
        String write7 = null;
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./assets/recordInputs/Inputs.txt"))) {
            while ((fileLine = reader.readLine()) != null) {
                if (fileLine.charAt(fileLine.length() - 1) == ';') {
                    String task = fileLine.split(" ")[0];
                    if (task.equalsIgnoreCase("CREATE")) {
                        String keyword = fileLine.split(" ")[1];
                        if (keyword.equalsIgnoreCase("database")) {
                            createDatabaseCount = createDatabaseCount + 1;
                        } else if (keyword.equalsIgnoreCase("table")) {
                            createTableCount = createTableCount + 1;
                        }
                    } else if (task.equalsIgnoreCase("DROP")) {
                        String keyword = fileLine.split(" ")[1];
                        if (keyword.equalsIgnoreCase("database")) {
                            dropDatabaseCount = dropDatabaseCount + 1;
                        } else if (keyword.equalsIgnoreCase("table")) {
                            dropTableCount = dropTableCount + 1;
                        }
                    } else if (task.equalsIgnoreCase("UPDATE")) {
                        String set = fileLine.split(" ")[2];
                        String where = fileLine.split(" ")[4];
                        String tableName = fileLine.split(" ")[1];
                        if(set.equalsIgnoreCase("SET") && where.equalsIgnoreCase("WHERE")){
                            updateTableCount = updateTableCount + 1;
                            list.add(tableName);
                        }

                    } else if (task.equalsIgnoreCase("INSERT")) {
                        String tablename1 = fileLine.split(" ")[2];
                        if (Validator.validateInsertQuery(fileLine)) {
                            insertTableCount = insertTableCount + 1;
                            list1.add(tablename1);

                        }
                    }else if (task.equalsIgnoreCase("DELETE")) {
                        if (Validator.validateDeleteQuery(fileLine)) {
                            deleteTableCount = deleteTableCount + 1;
                        }
                    }
                }
                else {
                    continue;
                }
            }
            String write5 = "Total number of valid Create Database Queries: " + createDatabaseCount + "\n" +
                    "Total number of valid Create Table Queries: " + createTableCount  + "\n" +
                    "Total number of valid Drop Database Queries: " + dropDatabaseCount + "\n" +
                    "Total number of valid Drop Table Queries: " + dropTableCount + "\n" +
                    "Total number of valid Update Table Queries: " + updateTableCount + "\n" +
                    "Total number of valid Insert Table Queries: " + insertTableCount + "\n" +
                    "Total number of valid Delete Table Queries: " + deleteTableCount;
            Set<String> unique = new HashSet<String>(list);
            for (String key : unique) {
                write6 = "Total " + Collections.frequency(list, key) + " Update operations performed on " + key + " table";
            }
            Set<String> unique1 = new HashSet<String>(list1);
            for (String key : unique1) {
                write7 = "Total " + Collections.frequency(list1, key) + " Insert operations performed on " + key + " table";
            }

            System.out.println(write5 + "\n" + write6 + "\n" + write7);
            File file = new File("./assets/analytics/analytics.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(write5);
            pr.println(write6);
            pr.println(write7);
            pr.close();
            br.close();
            fr.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
