package Operations;

import Dump.DumpManager;
import LogManagement.LogManager;
import MetaData.MetaDataGenerator;
import QueryParser.Parser;
import UserInterface.Menu;

import java.io.IOException;

public class MenuOperation {

    public void performTask() {
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
                    MetaDataGenerator metaDataGenerator = new MetaDataGenerator();
                    String databaseName = metaDataGenerator.takeInput();
                    try {
                        if(metaDataGenerator.validateDatabase(databaseName)) {
                            metaDataGenerator.generateMetaData(databaseName);
                        }else{
                            System.out.println("Database '" + databaseName + "' not found. Please try again.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
