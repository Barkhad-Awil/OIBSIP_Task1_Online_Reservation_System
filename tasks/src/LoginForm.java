import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginForm{
    private final String NAME_KEY = "Name";
    private final String PASSWORD_KEY = "Password";
    private Map<String, HashMap<String, Object>> userDataBase;

    LoginForm(RegistrationForm registrationForm){
           this.userDataBase = registrationForm.getDataBase();
    }

    public Boolean login(){
        System.out.println();
        System.out.println("You have successfully registered!.");
        System.out.println("Do you like to proceed with login form, (yes/no)?.");
        Scanner input = new Scanner(System.in);
        String decision = input.nextLine();

        if(!decision.equals("yes")){
            System.out.println("Thanks for registering");
            return false;
        }
            System.out.println();
            System.out.println("login form: ");
            System.out.println("Please enter your name and your password: ");
            //Verify user name
            System.out.print("Enter user name: ");
            String userName;

            while (true) {
                userName = input.nextLine();
                if (isNameValid(userName)) {
                    break;
                }
                System.out.println("Incorrect name!, please enter correct name: ");
            }

            //Verify user password
            System.out.print("Enter user password: ");
            int userPassword;

            while (true) {
                userPassword = input.nextInt();
                if (isPasswordValid(userName, userPassword)) {
                    break;
                }
                System.out.println("Incorrect id!, please enter valid id. ");
            }
        return isPasswordValid(userName, userPassword) && isNameValid(userName);
    }

    private boolean isPasswordValid(String name, int id) {
        for (Map.Entry<String, HashMap<String, Object>> entry: userDataBase.entrySet()){
            if(entry.getValue().containsKey(NAME_KEY) && entry.getValue().get(NAME_KEY).equals(name)){
                return entry.getValue().containsKey(PASSWORD_KEY) && entry.getValue().get(PASSWORD_KEY).equals(id);
            }
        }
        return false;
    }


    private boolean isNameValid(String userName) {
       return userDataBase.get("user1").get((String) "Name").equals(userName);
    }
}
