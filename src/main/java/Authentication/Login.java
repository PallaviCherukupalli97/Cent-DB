package Authentication;

import Operations.*;
import Preferences.DatabaseSetting;

import java.io.*;
import java.util.Scanner;

import LogManagement.LogManager;

public class Login {
    static String username, password;
    static Scanner input;

    public static void loginUser() throws IOException{
        input = new Scanner(System.in);

        System.out.print("Enter your username: ");
        username = input.nextLine();
        System.out.print("Enter password: ");
        password = input.nextLine();

        validateUser(username, password);

    }

    private static void validateUser(String username, String password) throws IOException{
        try {
            String eachLine = "";
            BufferedReader br = new BufferedReader(new FileReader( System.getProperty("user.dir") + "/assets/auth/User_Profile.txt"));
            while((eachLine = br.readLine()) != null) {
                String[] values = eachLine.split("\n");
                for (String val: values) {
                    String hash = Hash.getHash(username);
                    if(val.startsWith(hash)) {
                        String ogPassword = (val.split(" ; ")[1]).replace(" ","");
                        String hashedPwd = Hash.getHash(password);
                        if(ogPassword.equals(hashedPwd)) {
                            System.out.println((val.split(" ; ")[2]));
                            Scanner sc = new Scanner(System.in);
                            String answer = sc.nextLine();
                            if(answer.equals((val.split(" ; ")[3]).replace(" ",""))) {
                                System.out.println("Access granted: User " + username + " logged in successfully.");
                                DatabaseSetting.ACTIVE_USER = username;
                                MenuOperation operation = new MenuOperation();
                                    operation.performTask();
//                                }
                            }
                            else {
                                System.out.println("Access denied");
                            }
                        }
                        else {
                            System.out.print("Access denied");
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Error occurred: " + e.toString());
            LogManager.crashReport(e);
        }
    }




}
