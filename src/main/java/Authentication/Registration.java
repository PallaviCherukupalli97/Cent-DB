package Authentication;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Registration {
    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);
//Take inputs
        System.out.print("\nPlease enter a username of your choice: ");
        String userID = scanner.nextLine();
        System.out.print("Please enter a password of your choice: ");
        String password = scanner.nextLine();
        System.out.print("Please enter a security question: ");
        String securityQuestion = scanner.nextLine();
        System.out.print("Please enter answer to your security question: ");
        String securityAnswer = scanner.nextLine();

// Hashing user ID and Password
        String hashedUserID = Hash.getHash(userID);
        String hashedPassword = Hash.getHash(password);
// Write into a file
        createFile(hashedUserID, hashedPassword, securityQuestion, securityAnswer);
    }

    private static void createFile(String hashedUserID, String hashedPassword, String securityQuestion, String securityAnswer) {
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/assets/auth/User_Profile.txt", true);
            if (newUser(hashedUserID)) {
                //Data
                myWriter.write("\r\n" + hashedUserID + " ; " + hashedPassword + " ; " + securityQuestion + " ; " + securityAnswer);
                myWriter.close();
                System.out.println("User registered successfully.");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    private static boolean newUser(String hashedID) throws IOException {
        String eachLine = "";
        //Check if user exists
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/assets/auth/User_Profile.txt"));
        while ((eachLine = br.readLine()) != null) {
            String[] lines = eachLine.split("\n");
            for (String line : lines) {
                String[] values = line.split(" ; ");
                if (hashedID.equals(values[0])) {
                    System.out.println("User already exists");
                    return false;
                }
            }
        }
        return true;
    }
}