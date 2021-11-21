import Authentication.Login;
import Authentication.Registration;
import UserInterface.Menu;

public class CentDB {
    public static void main(String[] args) {

        //This is the entry point for the application
        System.out.println("Welcome to CentDB Application..!");
        int choice;

        while(true) {
            choice = Menu.mainMenu();
            switch (choice) {
                case 1 -> {
//                    System.out.println("Call Registration code");
                    Registration.registerUser();
                    //Register new user
                }
                case 2 -> {
//                    System.out.println("Call Login code");
                    Login.loginUser();
                    // Login User
                    
                }
                case 0 -> {
                    System.out.println("Exiting application...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid selection! Please try again.");

            }
        }
    }
}
