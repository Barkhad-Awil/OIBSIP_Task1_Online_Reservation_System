import java.util.Map;
import java.util.Scanner;

public class CancellationForm {
    static boolean isCancelled;
    CancellationForm(int pnr){
    }

    public static void cancelFlightReservation(Map<Integer, Map<Integer, Object>> reservations, String decision, Scanner input){
        System.out.print("Enter the pnr for the flight you want to cancel: ");
        int pnr = input.nextInt();
        if (decision.equalsIgnoreCase("yes") && !isCancelled()) {
            isCancelled = true;
            reservations.remove(pnr);
            System.out.println("Flight number " + pnr + " has been cancelled.");
        } else {
            System.out.println("Flight number " + pnr + " has successfully booked.");
        }
    }

    public static boolean isCancelled(){
        return isCancelled;
    }
}
