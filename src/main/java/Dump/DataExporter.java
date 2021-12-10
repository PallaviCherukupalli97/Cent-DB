package Dump;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataExporter{
	
	
	public String takeInput() {
        System.out.println("Enter the name of the database you want to export: ");
        Scanner input = new Scanner(System.in);
        String temp = input.nextLine();
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
     public void generateDataDump(String databaseName) throws IOException {
     
        File dataDumpFile = new File(System.getProperty("user.dir") + "/assets/dumps/" + databaseName + "_data_dump.sql");
        dataDumpFile.createNewFile();

        File tables = new File(System.getProperty("user.dir") + "/assets/database/" + databaseName + "/");
        List<String> list_of_tables = List.of(tables.list());

        FileWriter fileWriter = new FileWriter(dataDumpFile);
        fileWriter.write("CREATE DATABASE " + databaseName + ";");

        
        for (String table : list_of_tables) {
            
        	String tableName = table.substring(0, table.indexOf("."));
        	
            String firstLine = readLinesOfTable(databaseName, table, true).get(0);
            String tableStructure = generateTableStructure(tableName, firstLine.trim().split(":"));
            fileWriter.write("\n " + tableStructure);
            String tableData = generateTableDataDump(databaseName, tableName, firstLine.trim().split(":"));
            fileWriter.write("\n " + tableData);
                      
        }
        fileWriter.write("\n");
        fileWriter.close();

    }

    public String generateTableStructure(String tableName, String[] columnsList)
    {
        StringBuilder tableStructure = new StringBuilder();
        tableStructure.append("CREATE TABLE " + tableName + " ( ");
        
        for(String col: columnsList)
        {
        	tableStructure.append(col);
        	tableStructure.append(" , ");
        }
        
        tableStructure.deleteCharAt(tableStructure.lastIndexOf(","));
        
        tableStructure.append(" ); ");

        return tableStructure.toString();
    }

    public String generateTableDataDump(String databaseName, String tableName, String[] columnsList)
    {
    	StringBuilder tableDataRecordBuilder = new  StringBuilder();
        StringBuilder tableDataBuilder = new StringBuilder();
        
        tableDataBuilder.append("INSERT INTO " + tableName + " ( ");

        for(String col: columnsList)
        {        	tableDataBuilder.append(col.trim().split(" ")[0]);
        			tableDataBuilder.append(" , ");
        }

        tableDataBuilder.deleteCharAt(tableDataBuilder.lastIndexOf(","));

        tableDataBuilder.append(" ) VALUES( ");
        
        List<String> records = readLinesOfTable(databaseName, tableName + ".txt", false);
        int recordCounter = 0;
        for(String lineData: records)
        {
        	if(recordCounter == 0)
        	{
        		recordCounter++;
        		continue;
        	}
        	        	
          tableDataRecordBuilder.append(tableDataBuilder.toString() + lineData.replace(':', ',') + " ); \n");
        }

        return tableDataRecordBuilder.toString();
    }

    private List<String> readLinesOfTable(String databaseName,String table, boolean onlyFirstRecord) {
        List<String> records = new ArrayList<String>();
        String lineText = "";
        int lineCounter = 0;
        try{
            File file = new File(System.getProperty("user.dir") + "/assets/database/" + databaseName + "/" + table);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((lineText = br.readLine()) != null) {
            	
            	records.add(lineText);
            	
                if (onlyFirstRecord && lineCounter == 0)
                {
                	records.add(lineText);
                	break;
                }
                
                lineCounter++;
            }
        }catch (Exception e){
            System.out.println("Exception occurred: " + e.toString());
        }
        return records;
    }
    
    
}