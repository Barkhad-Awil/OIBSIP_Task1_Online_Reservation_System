import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlightReservationSystems {
    private final RegistrationForm registrationForm;
    private Reservation flightReservation;
    Map<Integer, Map<Integer, Object>> reservations = new HashMap<Integer, Map<Integer, Object>>();

    public FlightReservationSystems() {
        registrationForm = new RegistrationForm();
    }

    public void bookFlight() {
        System.out.println();
        System.out.println("Booking form:");
        System.out.println("Please fill the booking Form");
        Scanner input = new Scanner(System.in);
        System.out.print("Select seat number form 0 - 9: ");
        int selectedSeat = input.nextInt();
        input.nextLine();
        System.out.print("Enter your current place (originCountry): ");
        String originCountry = input.nextLine();
        System.out.print("Enter your destinationCountry: ");
        String destinationCountry = input.nextLine();
        System.out.print("Select class type (business or economic): ");
        String classType = input.nextLine();
        System.out.println();


        String name = (String) registrationForm.getDataBase().get("user1").get("Name");
        String email = (String) registrationForm.getDataBase().get("user1").get("Email");
        flightReservation = new Reservation(name, email , originCountry, destinationCountry, classType);
        flightReservation.setSeatNumber(selectedSeat);
        flightReservation.printBookingInfo();
        reservations.put(flightReservation.getPnrID(), flightReservation.makeReservation(name, email));
    }

    private void manageReservations(Map<Integer, Map<Integer, Object>> reservations) {
        System.out.println("Reservation Management:");
        System.out.println("1. View all reservations");
        System.out.println("2. Cancel a reservation");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllReservations(reservations);
                    break;
                case 2:
                    System.out.println();
                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter the PNR of the flight to cancel:");
                    int pnr = input.nextInt();
                    cancelReservation(reservations, pnr);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }


    public boolean hasBookedFlight() {
        return !reservations.isEmpty();
    }

    public void viewAllReservations(Map<Integer, Map<Integer, Object>> reservations) {
        System.out.println("All Reservations:");
        for (Map.Entry<Integer, Map<Integer, Object>> entry : reservations.entrySet()) {
            System.out.println("Booking with PNR " + entry.getKey() + " = " + entry.getValue().toString());
        }
    }

    private void cancelReservation(Map<Integer, Map<Integer, Object>> reservations, int pnr) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to proceed with flight cancellation");
        String decision = input.nextLine();
        for (Map.Entry<Integer, Map<Integer, Object>> reservation : reservations.entrySet()) {
            if (reservation.getKey() == pnr) {
                CancellationForm.cancelFlightReservation(reservations,decision, input);
                viewAllReservations(reservations);
            }
        }
        System.out.println("Reservation with PNR " + pnr + " not found.");
    }


public void run() {
    registrationForm.userData();
    LoginForm loginForm = new LoginForm(registrationForm);
    boolean isLoggedIn = loginForm.login();

    if (isLoggedIn) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Book a flight");
            System.out.println("2. Manage reservations");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookFlight();
                    break;
                case 2:
                    if(hasBookedFlight()){
                        manageReservations(reservations);
                    } else {
                        System.out.println("Please first book flight.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3); // Continue until the user chooses to exit
    } else {
        System.out.println("Login failed. Exiting application.");
    }
}

}
