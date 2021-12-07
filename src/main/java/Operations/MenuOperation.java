package Operations;

import java.io.File;
import Dump.DumpManager;
import LogManagement.LogManager;
import Preferences.DatabaseSetting;
import Analytics.AnalyticsGenerator;
import DataDictionary.DataDictionary;
import QueryParser.Parser;
import UserInterface.*;
import Transactions.Transactions;

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
                    Transactions transactions = new Transactions();
                    File sourceDirectory = new File(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE);
                    File targetDirectory = new File(System.getProperty("user.dir") + "/assets/database/transactionDatabase");
                    if(query.toLowerCase().startsWith("begin"))
                    {
                        transactions.copyDatabase(sourceDirectory, targetDirectory);
                        DatabaseSetting.TRANSACTION_DATABASE = DatabaseSetting.SELECTED_DATABASE;
                        transactions.changeGlobalDb("transactionDatabase");
                        //take input
                        while(true){
                            if(query.toLowerCase().startsWith("commit") || query.toLowerCase().startsWith("rollback")) {
                                if(query.toLowerCase().startsWith("commit"))
                                {
                                    // replace original db with transactiondb
                                    transactions.copyDatabase(targetDirectory, sourceDirectory);
                                    System.out.println("Transaction committed successfully");
                                }else{
                                    System.out.println("Transaction rollback successfully");
                                }
                                DatabaseSetting.SELECTED_DATABASE = DatabaseSetting.TRANSACTION_DATABASE;
                                DatabaseSetting.TRANSACTION_DATABASE = null;
                                break;
                            }
                            query = parser.takeInput();
                            if(!query.toLowerCase().startsWith("commit")) {
                                parser.executeQuery(query);
                            }
                        }
                       //delete trans db
                        transactions.deleteTransactionDatabase(targetDirectory);
                    }
                    else
                    {
                        LogManager.queryLog(query);
                        DumpManager.exportDump(query);
                        if (parser.validQuery(query)) {
                            parser.executeQuery(query);
                        }
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

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
