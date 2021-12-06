package Operations;

import MetaData.MetaDataGeneratorClass;
import QueryParser.Parser;
import UserInterface.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuOperation {
    public void performTask() throws IOException {
        String choice = "";

        while (true){

            choice = Menu.operationMenu();

            switch (choice){
                case "1":
                    Parser parser = new Parser();
                    String query = parser.takeInput();
                    if(parser.validQuery(query)){
                        parser.executeQuery(query);
                    }

//                write queries
                    break;

                case "2":
                    MetaDataGeneratorClass metaDataGeneratorclass = new MetaDataGeneratorClass();
                    String temp1 = metaDataGeneratorclass.takeInput();
                    if(metaDataGeneratorclass.validateDatabase(temp1)) {
                        metaDataGeneratorclass.MetaDataGenerator(temp1);
                    }

//
//                    String databaseName = metaDataGeneratorclass.takeInput();
//                    if(metaDataGeneratorclass.validateDatabase(databaseName)){
//                        metaDataGeneratorclass.MetaDataGenerator(databaseName);


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
