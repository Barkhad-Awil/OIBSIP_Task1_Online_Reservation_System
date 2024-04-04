import java.time.LocalDateTime;
import java.util.*;

public class Reservation {
    private int pnrID;
    int reservationNum =1;
    String passengerName;
    String passengerEmail;
    String airline = "Fly Emirates";
    String destinationCountry;
    String originCountry;
    String classType;
    String passengerSeatNumber;

    LocalDateTime bookingDate;

    List<String> seatNumbers = new ArrayList<>(Arrays.asList("1R", "3R", "5R", "7M", "9M", "11M", "13L", "15L", "17L", "L"));
    private final List<Boolean> bookAvailableSeats = new ArrayList<>(Collections.nCopies(seatNumbers.size(), true));

    public Reservation(String passengerName, String passengerEmail, String originCountry, String destinationCountry, String classType) {
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.classType = classType;

        Random random = new Random();
        this.pnrID = random.nextInt(900) + 100;
        this.bookingDate = LocalDateTime.now();
    }

    //class type getter and setter
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


    public void setSeatNumber(int seat){
        if(updateBookingSeats(seat)){
              this.passengerSeatNumber = seatNumbers.get(seat);
              bookAvailableSeats.set(seat, false);
        } else {
            System.out.println("This seat is booked!. Select another seat");
        }
    }

    private boolean updateBookingSeats(int seat) {
        return bookAvailableSeats.get(seat);
    }

    public void printBookingInfo(){
        System.out.println("Thank you for choosing " + airline +" for your upcoming journey. \nYour booking details are as follows:");
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



    public Integer getPnrID() {
        return pnrID;
    }

    public Map<Integer, Object> makeReservation(String passengerName, String passengerEmail){
        Map<Integer, Object> reservations = new HashMap<>();

        // Add the reservation to the hashmap with its PNR ID as the key
        reservations.put(getPnrID(), this);

        return reservations;
    }
    @Override
    public String toString() {
        return "[" +  passengerName + ", " + passengerEmail + ", " + originCountry + ", " + destinationCountry + ", " + classType + "]";
    }

}
