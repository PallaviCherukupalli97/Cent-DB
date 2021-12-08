package DataModel;

import java.io.*;
import java.util.List;

public class DataModel {

    public void exportDataModel() {
        try {

            File file = new File(System.getProperty("user.dir") + "/assets/data_dictionary/");
            List<String> allDictionaries = List.of(file.list());

            File dataModel = new File(System.getProperty("user.dir") + "/assets/data_model/ERD.txt");
            dataModel.createNewFile();
            FileWriter fileWriter = new FileWriter(dataModel);

            for(String dictionary: allDictionaries){
                File dict = new File(System.getProperty("user.dir") + "/assets/data_dictionary/" + dictionary);
                try (BufferedReader br = new BufferedReader(new FileReader(dict))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        fileWriter.write(line + "\n");
                    }
                }
                fileWriter.write("-----------------------------------------------------\n");
            }

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
