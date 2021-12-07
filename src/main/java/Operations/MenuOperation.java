package Operations;

import Analytics.AnalyticsGenerator;
import Dump.DumpManager;
import LogManagement.LogManager;
import DataDictionary.DataDictionary;
import QueryParser.Parser;
import UserInterface.*;

import java.io.IOException;

public class MenuOperation {

    public void performTask() throws IOException {
        String choice = "";
        while (true) {
            choice = Menu.operationMenu();
            switch (choice) {
                case "1":
                    Parser parser = new Parser();
                    String query = parser.takeInput();
                    LogManager.queryLog(query);
                    DumpManager.exportDump(query);
                    if (parser.validQuery(query)) {
                        parser.executeQuery(query);
                    }
                    break;

                case "2":


//                Export
                    break;

                case "3":
                    System.out.println("Implement data model feature here");
//                Data Model
                    break;

                case "4":
                    AnalyticsGenerator analyticsInfo = new AnalyticsGenerator();
                    analyticsInfo.countTotalQueries();
                    analyticsInfo.numberOfDatabases();
                    analyticsInfo.numberOfTables();
                    analyticsInfo.numberOfTablesInEachDatabase();
                    analyticsInfo.numberOfValidQueries();
//                Analytics
                    break;

                case "5":
                    DataDictionary dataDictionary = new DataDictionary();
                    String databaseName = dataDictionary.takeInput();
                    try {
                        if(dataDictionary.validateDatabase(databaseName)) {
                            dataDictionary.generateDataDictionary(databaseName);
                        }else{
                            System.out.println("Database '" + databaseName + "' not found. Please try again.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                Data Dictionary
                    break;

                case "6":
                    System.out.println("Implement logout feature here");
//                logout
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
