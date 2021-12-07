package Operations;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Dump.DumpManager;
import LogManagement.LogManager;
import Preferences.DatabaseSetting;
import QueryParser.Parser;
import UserInterface.*;
import Transactions.Transactions;

public class MenuOperation {

    public void performTask() {
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
                        transactions.changeGlobalDb();
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
                                break;
                            }
                            query = parser.takeInput();
                            if(!query.toLowerCase().startsWith("commit")) {
                                parser.executeQuery(query);
                            }
                        }
                       //delete trans db
                        transactions.deleteTransactionDatabase();
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
                    System.out.println("Implement Export feature here");
//                Export
                    break;

                case "3":
                    System.out.println("Implement data model feature here");
//                Data Model
                    break;

                case "4":
                    System.out.println("Implement analytics feature here");
//                Analytics
                    break;
                case "5":
                    System.out.println("Implement logout feature here");
//                logout
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
