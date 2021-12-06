package Operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Dump.DumpManager;
import LogManagement.LogManager;
import QueryParser.Parser;
import UserInterface.*;

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
