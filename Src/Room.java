public class Room {

    private int roomNumber;
    private RoomType roomType;  
    private boolean isAvailable;
    private double pricePerNight;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
        this.pricePerNight = calculatePrice(roomType);
            
    }

    private double calculatePrice(RoomType roomType) {
        switch (roomType) {
            case SINGLE:
                return 100.0; 
            case DOUBLE:
                return 150.0; 
            default:
                return 0.0;
        }
    }
     public int getRoomNumber() {
        return roomNumber;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public String getRoomInfo() {
        return "Room Number: " + roomNumber + ", Type: " + roomType + ", Available: " + isAvailable + ", Price per Night: $" + pricePerNight;
    }

    
}
