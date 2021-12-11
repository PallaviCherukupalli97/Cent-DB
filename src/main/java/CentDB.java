import Authentication.Login;
import Authentication.*;
import Preferences.*;
import UserInterface.*;

public class CentDB {
    public static void main(String[] args) {

        System.out.println("Welcome to CentDB Application..!");
        DatabaseSetting.SELECTED_DATABASE = null;
        int choice;

            choice = Menu.mainMenu();
            switch (choice) {
                case 1 : {
//                    System.out.println("Call Registration code");
                    Registration.registerUser();
                    //Register new user
                }
                case 2 : {


//                    System.out.println("Call Login code");
                    Login.loginUser();
                    // Login User
                }

                case 0 : {
                    System.out.println("Exiting application...");
                    System.exit(0);
                }
                default : System.out.println("Invalid selection! Please try again.");

        }
    }
}
