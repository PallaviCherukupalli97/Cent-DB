package Transactions;

import Preferences.DatabaseSetting;
import org.apache.commons.io.FileUtils;

import LogManagement.LogManager;

import java.io.File;
	
public class Transactions {
	LogManager logmanager = new LogManager();
    public static void copyDatabase(File sourceFile, File targetFile){
        try
        {
            FileUtils.copyDirectory(sourceFile, targetFile);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public void changeGlobalDb(String database){
        DatabaseSetting.SELECTED_DATABASE = database;
        logmanager.eventLog(database);
    }
    public void deleteTransactionDatabase(File directory){
        try {
            FileUtils.deleteDirectory(directory);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
