import java.util.Date;

public class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private int numberOfNights;
    private double totalCost;
    private Date checkInDate;
    private Date checkOutDate;
    
    public Reservation(int reservationId, Guest guest, Room room, int numberOfNights, Date checkInDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.checkInDate = checkInDate;
        this.checkOutDate = calculateCheckOutDate(checkInDate, numberOfNights);
        this.totalCost = calculateTotalCost(room.getPricePerNight(), numberOfNights);
    }
    private Date calculateCheckOutDate(Date checkInDate, int numberOfNights) {
        long checkInTime = checkInDate.getTime();
        long checkOutTime = checkInTime + (numberOfNights * 24 * 60 * 60 * 1000L);
        return new Date(checkOutTime);
    }
    private double calculateTotalCost(double pricePerNight, int numberOfNights) {
        return pricePerNight * numberOfNights;
    }

    public int getReservationId() {
        return reservationId;
    }
    public Guest getGuest() {
        return guest;
    }
    public Room getRoom() {
        return room;
    }
    public int getNumberOfNights() {
        return numberOfNights;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    
    public String getReservationInfo() {
        return "Reservation ID: " + reservationId + ", Guest: " + guest.getName() + ", Room: " + room.getRoomNumber() +
                ", Nights: " + numberOfNights + ", Total Cost: $" + totalCost +
                ", Check-In: " + checkInDate + ", Check-Out: " + checkOutDate;
    }

    public void updateNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
        this.checkOutDate = calculateCheckOutDate(checkInDate, numberOfNights);
        this.totalCost = calculateTotalCost(room.getPricePerNight(), numberOfNights);
    }
    
    public void cancelReservation() {
        room.setAvailable(true);
    }
    
}