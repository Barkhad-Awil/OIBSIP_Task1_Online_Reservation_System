import java.time.LocalDateTime;
import java.util.*;

// Class to manage flight reservations
public class Reservation {
    private int pnrID; // Booking reference ID
    private String passengerName; // Name of the passenger
    private String passengerEmail; // Email of the passenger
    private String airline = "Fly Emirates"; // Airline company
    private String destinationCountry; // Destination country
    private String originCountry; // Origin country
    private String classType; // Class type (e.g., economy, business)
    private String passengerSeatNumber; // Seat number
    private LocalDateTime bookingDate; // Booking date and time

    // List of available seat numbers
    List<String> seatNumbers = new ArrayList<>(Arrays.asList("1R", "3R", "5R", "7M", "9M", "11M", "13L", "15L", "17L", "18L"));
    // List to track available seats
    private final List<Boolean> bookAvailableSeats = new ArrayList<>(Collections.nCopies(seatNumbers.size(), true));

    // Constructor to initialize reservation details
    public Reservation(String passengerName, String passengerEmail, String originCountry, String destinationCountry, String classType) {
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.classType = classType;

        // Generate a random PNR ID and set booking date to current date and time
        Random random = new Random();
        this.pnrID = random.nextInt(900) + 100;
        this.bookingDate = LocalDateTime.now();
    }

    // Method to retrieve class type
    public String getClassType() {
        return classType;
    }

    // Method to assign a seat number to the passenger
    public void setSeatNumber(int seat) {
        if (updateBookingSeats(seat)) {
            this.passengerSeatNumber = seatNumbers.get(seat);
            bookAvailableSeats.set(seat, false);
        } else {
            System.out.println("This seat is booked! Please select another seat.");
        }
    }

    // Method to update available seats
    private boolean updateBookingSeats(int seat) {
        return bookAvailableSeats.get(seat);
    }
    // Method to check if a seat is available
    public boolean isSeatAvailable(int seatNumber) {
        List<String> seatNumbers = new ArrayList<>(Arrays.asList("1R", "3R", "5R", "7M", "9M", "11M", "13L", "15L", "17L", "L"));
        // Check if the seat number is within the valid range
        if (seatNumber >= 0 && seatNumber < seatNumbers.size()) {
            // Get the seat number from the list
            String seat = seatNumbers.get(seatNumber);
            // Check if the seat is available by looking it up in the list of booked seats
            return bookAvailableSeats.get(seatNumbers.indexOf(seat));
        } else {
            // If the seat number is invalid, return false
            return false;
        }
    }

    // Method to print booking information
    public void printBookingInfo() {
        System.out.println("Thank you for choosing " + airline + " for your upcoming journey.");
        System.out.println("Your booking details are as follows:");
        int columnWidth = 25;
        System.out.println("Booking Details:");
        System.out.printf("%-" + columnWidth + "s : %s\n", "Passenger name", passengerName);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Passenger email", passengerEmail);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Booking date", bookingDate);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Origin country", originCountry);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Destination country", destinationCountry);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Passenger seat number", passengerSeatNumber);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Flight number", pnrID);
        System.out.printf("%-" + columnWidth + "s : %s\n", "Passenger class type", getClassType());
        System.out.println();
    }

    // Method to retrieve PNR ID
    public Integer getPnrID() {
        return pnrID;
    }

    // Method to make a reservation and return a map containing the reservation details
    public Map<Integer, Object> makeReservation(String passengerName, String passengerEmail) {
        Map<Integer, Object> reservations = new HashMap<>();

        // Add the reservation to the hashmap with its PNR ID as the key
        reservations.put(getPnrID(), this);

        return reservations;
    }

    // Method to represent the object as a string
    @Override
    public String toString() {
        return "[" + passengerName + ", " + passengerEmail + ", " + originCountry + ", " + destinationCountry + ", " + classType + "]";
    }
}
