package UserInterface;
import java.util.Scanner;

public class Menu {
    static Scanner input = new Scanner(System.in);

    public static int mainMenu() {
        System.out.println("\n1. Register new user");
        System.out.println("2. Login user");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
        return input.nextInt();
    }

    public static String operationMenu(){
        System.out.println("1. Write Queries");
        System.out.println("2. Export");
        System.out.println("3. Data Model");
        System.out.println("4. Analytics");
        System.out.println("5. Export Data Dictionary");
        System.out.println("6. Logout");
        System.out.println("Select an option: ");
        return input.next();
    }

}
