import java.util.Map;
import java.util.Scanner;

public class CancellationForm {
    private static int PNR;
    static boolean isCancelled;
    CancellationForm(int pnr){
        this.PNR = pnr;
        isCancelled = false;
    }

    public static int getPNR() {
        return PNR;
    }

    public void setPNR(int PNR) {
        this.PNR = PNR;
    }

    public static void cancelFlightReservation(Map<Integer, Map<Integer, Object>> reservations, String decision, Scanner input){
        System.out.print("Enter the pnr for the flight you want to cancel: ");
        int pnr = input.nextInt();
        if (decision.equalsIgnoreCase("yes") && !isCancelled) {
            isCancelled = true;
            reservations.remove(pnr);
            System.out.println("Flight number " + pnr + " has been cancelled.");
        } else {
            System.out.println("Flight number " + pnr + " has successfully booked.");
        }
    }

    public boolean isCancelled(){
        return isCancelled;
    }
}
