package MetaData;

import java.io.*;
import java.util.*;

public class MetaDataGeneratorClass {

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

    public void MetaDataGenerator(String databaseName) throws IOException {
        String tableLocation = "./assets/database/" + databaseName + "/";
        File Tables = new File(tableLocation);
        String tables[] = Tables.list();
        String Print = null;
        ArrayList<String> ar = new ArrayList<String>();
        for (int i = 0; i < tables.length; i++) {
            String tableName = tables[i];
            String tableDataLocation = "./assets/database/" + databaseName + "/" + tableName;
            try {
                FileInputStream fstream = new FileInputStream(tableDataLocation);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                strLine = br.readLine();
                String[] splitter = strLine.split(":");
                for (String nameDatatype : splitter) {
                    String[] types = nameDatatype.split(" ");
                    Print = tables[i] + ":" + types[0] + ":" + types[1];
                    ar.add(Print);

                }
                in.close();
            } catch (Exception e) {// Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
        BufferedWriter wr = null;
        try {
            wr = new BufferedWriter(new FileWriter("MetaData.txt"));
            wr.write("Table Name:Name:Datatype \n");
            for (String var : ar) {
                wr.write(var);
                wr.newLine();
            }
            wr.close();

        } catch (Exception e) {

        }

    }
}


