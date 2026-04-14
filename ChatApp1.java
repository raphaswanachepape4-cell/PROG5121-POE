import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatApp1 {
    public static Map<String, User> users = new HashMap<>();
    public static String loggedInUser = null;
    public static Scanner scanner = new Scanner(System.in);
//input choices.
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Check login status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
//initializng the user s choices.
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
//Registering the user into the app.
    public static void registerUser() {
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
//Loggin in the user to the app
    public static boolean loginUser() {
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
//checking if the user is locked in or what.
    public static String returnLoginStatus() {
        if (loggedInUser != null) {
            return "Logged in as " + loggedInUser;
        } else {
            return "Not logged in";
        }
    }
//checking for the correct input of the user.
    public static boolean checkInput(String userName, String password, String phoneNumber) {
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
//use's details requare
    public static boolean validateUserName(String userName) {
        return userName.matches("[a-zA-Z0-9_]{5,}");
    }

    public static boolean validatePassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$"); //requarements.
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }
}
//Declarations
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