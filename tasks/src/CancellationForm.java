import java.util.Map;
import java.util.Scanner;

// Class to handle flight cancellation form
public class CancellationForm {
    static boolean isCancelled; // Flag to track if a flight is cancelled

    // Method to cancel a flight reservation
    public static void cancelFlightReservation(Map<Integer, Object> reservations, String decision, Scanner input, int pnr) {
        if (decision.equalsIgnoreCase("yes") && !isCancelled()) {
            isCancelled = true;
            if (reservations.containsKey(pnr)) {
                reservations.remove(pnr);
                System.out.println("Flight number " + pnr + " has been cancelled.");
            } else {
                System.out.println("Flight number " + pnr + " not found.");
            }
        } else {
            System.out.println("Flight number " + pnr + " has not been cancelled.");
        }
    }

    // Method to check if a flight is cancelled
    public static boolean isCancelled() {
        return isCancelled;
    }
}
