import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Guest> guests = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();
        int numberOfGuests = 0;
        int numberOfTotalRooms = 68;
        int numberOfReservations = 0;
        

        for (int i = 1; i <= numberOfTotalRooms; i++) {
            RoomType roomType = (i <= 34) ? RoomType.SINGLE : RoomType.DOUBLE;
            rooms.add(new Room(i, roomType));
        }
        
        
        while (true) {
            System.out.println("----------------------------------------------------------------");
           // System.out.println();
            System.out.println("Welcome to the Hotel Reservation System");
            System.out.println("1. Add Guest");
            System.out.println("2. View Guests");
            System.out.println("3. Update Guest Phone Number");
            System.out.println("4. View Rooms");
            System.out.println("5. Make Reservation");
            System.out.println("6. View Reservations");
            System.out.println("7. Cancel Reservation");
            System.out.println("8. Show Available Rooms");
            System.out.println("9. Search Reservations by Guest Name");
            System.out.println("10. Exit");
            System.out.print("Please select an option: ");
            System.out.println();
            System.out.println("----------------------------------------------------------------");

            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    // Add Guest
                    System.out.print("Enter guest name: ");
                    String name = input.nextLine();
                    System.out.print("Enter guest phone number: ");
                    String phone = input.nextLine();
                    numberOfGuests++;
                    guests.add(new Guest(numberOfGuests, name, phone));
                    System.out.println("Guest added successfully.");
                    break;
                case 2:
                    // View Guests
                    if (guests.isEmpty()) {
                        System.out.println("No guests found.");
                    } else {
                        for (Guest guest : guests) {
                            System.out.println(guest.getGuestInfo());
                        }
                    }
                    break;
                case 3:
                    // Update Guest Phone Number
                    if (guests.isEmpty()) {
                        System.out.println("No guests found.");
                        break;
                    }
                    System.out.print("Enter guest ID to update phone number: ");
                    int guestIdToUpdate = input.nextInt();
                    input.nextLine();
                    boolean guestFound = false;
                    for (Guest guest : guests) {
                        if (guest.getId() == guestIdToUpdate) {
                            guestFound = true;
                            System.out.print("Enter new phone number: ");
                            String newPhone = input.nextLine();
                            guest.updatePhone(newPhone);
                            System.out.println("Phone number updated successfully.");
                            break;
                        }
                    }
                    if (!guestFound) {
                        System.out.println("Guest not found.");
                    }
                    break;
                case 4:
                    // View Rooms
                    if (rooms.isEmpty()) {
                        System.out.println("No rooms found.");
                    } else {
                        for (Room room : rooms) {
                            System.out.println(room.getRoomInfo());
                        }
                    }
                    break;
                case 5:
                    // Make Reservation
                    if (guests.isEmpty()) {
                        System.out.println("No guests found. Please add a guest first.");
                        break;
                    }
                    else if (rooms.isEmpty()) {
                        System.out.println("No rooms found. Please add rooms first.");
                        break;
                    }
                    else {
                        System.out.print("Enter guest ID for reservation: ");
                        int guestIdForReservation = input.nextInt();
                        input.nextLine();
                        Guest selectedGuest = null;
                        for (Guest guest : guests) {
                            if (guest.getId() == guestIdForReservation) {
                                selectedGuest = guest;
                                break;
                            }
                        }
                        if (selectedGuest == null) {
                            System.out.println("Guest not found.");
                            break;
                        }

                        System.out.print("Enter room number for reservation: ");
                        int roomNumberForReservation = input.nextInt();
                        input.nextLine();
                        Room selectedRoom = null;
                        for (Room room : rooms) {
                            if (room.getRoomNumber() == roomNumberForReservation && room.isAvailable()) {
                                selectedRoom = room;
                                break;
                            }
                        }
                        if (selectedRoom == null) {
                            System.out.println("Room not found or not available.");
                            break;
                        }

                        System.out.print("Enter number of nights for reservation: ");
                        int numberOfNights = input.nextInt();
                        input.nextLine(); 
                        Date checkInDate = new Date(); 
                        numberOfReservations++;
                        Reservation newReservation = new Reservation(numberOfReservations, selectedGuest, selectedRoom, numberOfNights, checkInDate);
                        reservations.add(newReservation);
                        selectedRoom.setAvailable(false); 
                        System.out.println("Reservation made successfully.");
                    }

                    break;
                case 6:
                    // View Reservations
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        for (Reservation reservation : reservations) {
                            System.out.println(reservation.getReservationInfo());
                        }
                    }
                    break;
                case 7:
                    // Cancel Reservation
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations found.");
                        break;
                    }
                    else {
                        System.out.print("Enter reservation ID to cancel: ");
                        int reservationIdToCancel = input.nextInt();
                        input.nextLine(); 
                        boolean reservationFound = false;
                        for (Reservation reservation : reservations) {
                            if (reservation.getReservationId() == reservationIdToCancel) {
                                reservationFound = true;
                                reservation.cancelReservation();
                                reservations.remove(reservation);
                                System.out.println("Reservation canceled successfully.");
                                break;
                            }
                        }
                        if (!reservationFound) {
                            System.out.println("Reservation not found.");
                        }
                    }
                    break;
                case 8:
                    // Show Available Rooms
                    boolean availableRoomsFound = false;
                    for (Room room : rooms) {
                        if (room.isAvailable()) {
                            System.out.println(room.getRoomInfo());
                            availableRoomsFound = true;
                        }
                    }
                    if (!availableRoomsFound) {
                        System.out.println("No available rooms found.");
                    }
                    break;
                case 9:
                    // Search Reservations by Guest Name
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations found.");
                        break;
                    }
                    else {
                        System.out.print("Enter guest name to search reservations: ");
                        String guestNameToSearch = input.nextLine();
                        boolean reservationFoundByName = false;
                        for (Reservation reservation : reservations) {
                            if (reservation.getGuest().getName().equalsIgnoreCase(guestNameToSearch)) {
                                System.out.println(reservation.getReservationInfo());
                                reservationFoundByName = true;
                            }
                        }
                        if (!reservationFoundByName) {
                            System.out.println("No reservations found for the given guest name.");
                        }
                    }
                    break;
                case 10:
                    // Exit
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
        }
        
        
    }

}