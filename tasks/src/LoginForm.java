import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class to handle user login functionality
public class LoginForm {
    // Constants for keys in user data map
    private final String NAME_KEY = "Name";
    private final String PASSWORD_KEY = "Password";
    // Reference to user database from RegistrationForm
    private Map<String, HashMap<String, Object>> userDataBase;

    // Constructor to initialize the user database reference
    LoginForm(RegistrationForm registrationForm) {
        this.userDataBase = registrationForm.getDataBase();
    }

    // Method to perform user login
    public Boolean login() {
        System.out.println();
        System.out.println("You have successfully registered!.");
        System.out.println("Do you like to proceed with the login form? (yes/no)");
        Scanner input = new Scanner(System.in);
        String decision = input.nextLine();

        if (!decision.equals("yes")) {
            System.out.println("Thanks for registering.");
            return false;
        }

        System.out.println();
        System.out.println("Login Form:");
        System.out.println("Please enter your name and password:");

        // Verify user name
        System.out.print("Enter username: ");
        String userName;
        while (true) {
            userName = input.nextLine();
            if (isNameValid(userName)) {
                break;
            }
            System.out.println("Incorrect name! Please enter the correct name: ");
        }

        // Verify user password
        System.out.print("Enter password: ");
        int userPassword;
        while (true) {
            userPassword = input.nextInt();
            if (isPasswordValid(userName, userPassword)) {
                break;
            }
            System.out.println("Incorrect password! Please enter a valid password: ");
        }

        // Return true only if both name and password are valid
        return isPasswordValid(userName, userPassword) && isNameValid(userName);
    }

    // Method to validate password
    private boolean isPasswordValid(String name, int password) {
        for (Map.Entry<String, HashMap<String, Object>> entry : userDataBase.entrySet()) {
            if (entry.getValue().containsKey(NAME_KEY) && entry.getValue().get(NAME_KEY).equals(name)) {
                return entry.getValue().containsKey(PASSWORD_KEY) && entry.getValue().get(PASSWORD_KEY).equals(password);
            }
        }
        return false;
    }

    // Method to validate name
    private boolean isNameValid(String userName) {
        return userDataBase.get("user1").get(NAME_KEY).equals(userName);
    }
}
