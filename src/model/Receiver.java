package model;

public class Receiver {
    private int receiverId;
    private String name;
    private String phone;
    private String email;
    private String location;
    private int foodId;

    public Receiver() {
    }

    // Parameterized Constructor
    public Receiver(int receiverId, String name,String email, String phone, String location, int foodId) {
        this.receiverId = receiverId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.foodId = foodId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "receiverId=" + receiverId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", foodId=" + foodId +
                '}';
    }
}
