import Authentication.Login;
import Authentication.*;
import Preferences.*;
import UserInterface.*;

public class CentDB {
    public static void main(String[] args) {

        //This is the entry point for the application
        System.out.println("Welcome to CentDB Application..!");
        DatabaseSetting.SELECTED_DATABASE = null;
        int choice;

            choice = Menu.mainMenu();
            switch (choice) {
                case 1 : {
                    //Register new user
                    Registration.registerUser();
                }
                case 2 : {
                    // Login User
                    Login.loginUser();
                }

                case 0 : {
                    System.out.println("Exiting application...");
                    System.exit(0);
                }
                default : System.out.println("Invalid selection! Please try again.");

        }
    }
}
