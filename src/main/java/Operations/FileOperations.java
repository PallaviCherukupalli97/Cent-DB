package Operations;

import Preferences.DatabaseSetting;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileOperations {

    public void appendHashMap(String tableName, Map<String, String> tableRow) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/assets/database/" + DatabaseSetting.SELECTED_DATABASE + "/" + tableName + ".txt", true);
            for(String key: tableRow.keySet()){
                fileWriter.write(key + " '" + tableRow.get(key) + "' : ");
            }
            fileWriter.write("\n");
            System.out.println("1 row added to " + tableName);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
