import java.util.*;

// Class to manage flight reservation systems
public class FlightReservationSystems {
    private final RegistrationForm registrationForm; // Registration form instance
    private Reservation flightReservation; // Flight reservation instance
    // Map to store reservations with PNR as key
    Map<Integer, Object> reservations = new HashMap<>();

    // Constructor to initialize the registration form
    public FlightReservationSystems() {
        registrationForm = new RegistrationForm();
    }

    // Method to book a flight
    public void bookFlight() {
        System.out.println();
        System.out.println("Booking form:");
        System.out.println("Please fill out the booking form.");
        Scanner input = new Scanner(System.in);
        System.out.print("Select seat number from 0 - 9: ");
        int selectedSeat = input.nextInt();
        input.nextLine();
        System.out.print("Enter your current place (originCountry): ");
        String originCountry = input.nextLine();
        System.out.print("Enter your destinationCountry: ");
        String destinationCountry = input.nextLine();
        System.out.print("Select class type (business or economic): ");
        String classType = input.nextLine();
        System.out.println();

        // Retrieve user's name and email from registration data
        String name = (String) registrationForm.getDataBase().get("user1").get("Name");
        String email = (String) registrationForm.getDataBase().get("user1").get("Email");
        // Create a new flight reservation
        flightReservation = new Reservation(name, email, originCountry, destinationCountry, classType);
        flightReservation.setSeatNumber(selectedSeat);
        flightReservation.printBookingInfo();
        // Store reservation in the reservations map
        reservations.put(flightReservation.getPnrID(), flightReservation.makeReservation());
    }

    // Method to manage reservations
    private void manageReservations(Map<Integer, Object> reservations) {
        System.out.println("Reservation Management:");
        System.out.println("1. View all reservations");
        System.out.println("2. Cancel a reservation");
        System.out.println("3. Check available seats");
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
            case 3:
                checkAvailableSeats();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Method to check if a flight has been booked
    public boolean hasBookedFlight() {
        return !reservations.isEmpty();
    }

    // Method to view all reservations
    public void viewAllReservations(Map<Integer, Object> reservations) {
        System.out.println("All Reservations:");
        System.out.println(reservations);
        for (Map.Entry<Integer, Object> entry : reservations.entrySet()) {
            System.out.println("Booking with PNR " + entry.getKey() + " = " + entry.getValue().toString());
        }
    }

    private void checkAvailableSeats() {
        System.out.println("Available Seats:");
        System.out.println("Seat Number\tStatus");
        for (int i = 0; i < 10; i++) {
            if (flightReservation.isSeatAvailable(i)) {
                System.out.println(i + "\t\tAvailable");
            } else {
                System.out.println(i + "\t\tBooked");
            }
        }
    }

//     Method to cancel a reservation
    private void cancelReservation(Map<Integer, Object> reservations, int pnr) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to proceed with flight cancellation");
        String decision = input.nextLine();
        for (Map.Entry<Integer, Object> reservation : reservations.entrySet()) {
            if (reservation.getKey() == pnr) {
                CancellationForm.cancelFlightReservation(reservations, decision, pnr);
                break;
            }
        }
        System.out.println("Reservation with PNR " + pnr + " not found.");
    }



    // Method to run the flight reservation system
    public void run() {
        // Collect user registration data
        registrationForm.userData();
        LoginForm loginForm = new LoginForm(registrationForm);
        boolean isLoggedIn = loginForm.login();

        if (isLoggedIn) {
            int choice;
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println();
                System.out.println("What would you like to do?");
                System.out.println("1. Book a flight");
                System.out.println("2. Manage reservations");
                System.out.println("3. Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        bookFlight();
                        break;
                    case 2:
                        if (hasBookedFlight()) {
                            manageReservations(reservations);
                        } else {
                            System.out.println("Please first book a flight.");
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
