

public class Guest {
    private int id;
    private String name;
    private String phone;
    
    public Guest(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public String getGuestInfo() {
        return "Guest ID: " + id + ", Name: " + name + ", Phone: " + phone;
    }
    
   
}
