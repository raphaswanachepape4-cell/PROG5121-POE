//ChatApp.

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatApp {
    private static Map<String, User> users = new HashMap<>();
    private static String loggedInUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Check login status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
//options to choose from.
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    if (loginUser()) {
                        System.out.println("Login successful");
                    } else {
                        System.out.println("Incorrect password or username");
                    }
                    break;
                case 3:
                    System.out.println(returnLoginStatus());
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
//registering user.
    private static void registerUser() {
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter South Africa cell phone number: ");
        String phoneNumber = scanner.nextLine();

        if (checkInput(userName, password, phoneNumber)) {
            if (users.containsKey(userName)) {
                System.out.println("Username already exists.");
            } else {
                users.put(userName, new User(userName, password, phoneNumber));
                System.out.println("The account is registered successfully");
            }
        }
    }
//login the user into the app
    private static boolean loginUser() {
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(userName)) {
            User user = users.get(userName);
            if (user.getPassword().equals(password)) {
                loggedInUser = userName;
                return true;
            }
        }
        return false;
    }
//checking the user's login status.
    private static String returnLoginStatus() {
        if (loggedInUser != null) {
            return "Logged in as " + loggedInUser;
        } else {
            return "Not logged in";
        }
    }
//chacking if the user has the right requirements.
    private static boolean checkInput(String userName, String password, String phoneNumber) {
        boolean isGood = true;
        if (!validateUserName(userName)) {
            System.out.println("The username you have entered is incorrect. Please try again ");
            isGood = false;
        }
        if (!validatePassword(password)) {
            System.out.println("The password you have entered is incorrect. Please try again");
            isGood = false;
        }
        if (!validatePhoneNumber(phoneNumber)) {
            System.out.println("The cellphone number you have entered is incorrect. Please try again");
            isGood = false;
        }
        return isGood;
    }
//checking if they matche.
    private static boolean validateUserName(String userName) {
        return userName.matches("[a-zA-Z0-9_]{5,}");
    }

    private static boolean validatePassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$");
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }
}
//declaration
class User {
    private String userName;
    private String password;
    private String phoneNumber;

    public User(String userName, String password, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}