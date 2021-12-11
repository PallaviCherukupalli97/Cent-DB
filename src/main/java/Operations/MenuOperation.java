package Operations;

import java.io.File;

import DataModel.DataModel;
import Dump.DataExporter;
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
                            LogManager.queryLog(query);
                            if (!query.toLowerCase().startsWith("commit") && !query.toLowerCase().startsWith("rollback")) {
                                parser.executeQuery(query);
                            }
                        }
                        //delete trans db
                        transactions.deleteTransactionDatabase(targetDirectory);
                    } else {


                        if (parser.validQuery(query)) {
                            long startTime = System.nanoTime();
                            parser.executeQuery(query);
                            long stopTime = System.nanoTime();
                            long timer = stopTime - startTime;

                            if(DatabaseSetting.SELECTED_DATABASE!=null){
                                LogManager.queryLog(query);
                                DumpManager.dump(query);
                                LogManager.generalLog(timer);

                            }

                        }
                    }
                    break;

                case "2":
                    DataExporter dataExport = new DataExporter();
                    String database = dataExport.takeInput();
                    try {
                        if (database != null) {
                            if (dataExport.validateDatabase(database)) {
                                dataExport.generateDataDump(database);
                                System.out.println("Dump exported successfully");
                            } else {
                                System.out.println("Database '" + database + "' does not exist. Please try again.");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
                    analyticsInfo.numberOfUserOperations();
                    analyticsInfo.numberOfValidQueries();
//                Analytics
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
