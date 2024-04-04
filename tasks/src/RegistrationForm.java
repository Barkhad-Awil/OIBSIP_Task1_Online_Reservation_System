import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationForm {
    private static final String EMAIL_KEY = "Email";
    private static final String NAME_KEY = "Name";
    private static final String PASSWORD_KEY = "Password";
    private final Map<String, HashMap<String, Object>> usersData = new HashMap<>();

    public Map<String, HashMap<String, Object>> getDataBase() {
        return usersData;
    }

    public void userData() {
        Scanner input = new Scanner(System.in);
        System.out.println("User Registration Form:");
        HashMap<String, Object> user1Data = getUsersData(input);
        usersData.put("user1", user1Data);
    }

    public HashMap<String, Object> getUsersData(Scanner input) {
        HashMap<String, Object> userData = new HashMap<>();
        System.out.println("Enter your name: ");
        String userName = input.nextLine();
        System.out.println("Enter your password: ");
        int userPassword = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.println("Enter your email: ");
        String userEmail;
        while (true) {
            userEmail = input.nextLine();
            if (!isEmailValid(userEmail)) {
                System.out.println(userName + " please enter a valid email: ");
            } else {
                break;
            }
        }
        userData.put(NAME_KEY, userName);
        userData.put(PASSWORD_KEY, userPassword);
        userData.put(EMAIL_KEY, userEmail);
        return userData;
    }

    public boolean isEmailValid(String email) {
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
