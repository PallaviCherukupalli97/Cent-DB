package Operations;

import java.io.File;

import DataModel.DataModel;
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
	LogManager log = new LogManager();
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
                    if (query.toLowerCase().startsWith("begin")) {
                        transactions.copyDatabase(sourceDirectory, targetDirectory);
                        DatabaseSetting.TRANSACTION_DATABASE = DatabaseSetting.SELECTED_DATABASE;
                        transactions.changeGlobalDb("transactionDatabase");
                        //take input
                        while (true) {
                            if (query.toLowerCase().startsWith("commit") || query.toLowerCase().startsWith("rollback")) {
                                if (query.toLowerCase().startsWith("commit")) {
                                    // replace original db with transactiondb
                                    transactions.copyDatabase(targetDirectory, sourceDirectory);
                                    String successMsg = "Transaction committed successfully";
                                    System.out.println(successMsg);
                                    log.transactionMsg(successMsg);
                                } else {
                                	String rollbackMsg = "Transaction rollback successfully";
                                    System.out.println(rollbackMsg);
                                    log.transactionMsg(rollbackMsg);
                                }
                                DatabaseSetting.SELECTED_DATABASE = DatabaseSetting.TRANSACTION_DATABASE;
                                DatabaseSetting.TRANSACTION_DATABASE = null;
                                break;
                            }
                            query = parser.takeInput();
                            if (!query.toLowerCase().startsWith("commit") && !query.toLowerCase().startsWith("rollback")) {
                                parser.executeQuery(query);
                            }
                        }
                        //delete trans db
                        transactions.deleteTransactionDatabase(targetDirectory);
                    } else {
                        
                        
                        if (parser.validQuery(query)) {
                        	long startTime = System.nanoTime();
                        	long stopTime = System.nanoTime();
                        	long timer= stopTime - startTime;
                        	LogManager.queryLog(query);
                        	DumpManager.dump(query);
                        	LogManager.generalLog(timer);
                            parser.executeQuery(query);
                           
                        }
                    }
                    break;

                case "2":
                	DumpManager dump= new DumpManager();
                	dump.exportDump();
//                Export
                    break;

                case "3":
                    DataModel dataModel = new DataModel();
                    dataModel.exportDataModel();
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
                        if(DatabaseSetting.SELECTED_DATABASE != null){
                            if(dataDictionary.validateDatabase(databaseName)) {
                                dataDictionary.generateDataDictionary(databaseName);
                            }else{
                                System.out.println("Database '" + databaseName + "' not found. Please try again.");
                            }

                        }else {
                            System.out.println("No database selected");
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
