package model;

public class Food {
    private int Foodid;
    private String Foodname;
    private int quantity;
    private String expiryDate;
    private boolean allocated;

    public Food(int id, String name, int q, String expiry){
        Foodid=id;
        Foodname=name;
        quantity=q;
        expiryDate=expiry;
        allocated=false;
    }

    public int getFoodid(){
        return Foodid;
    }
    public String getFoodname(){
        return Foodname;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getExpiryDate(){
        return expiryDate;
    }
    public Boolean getAllocated(){
        return allocated;
    }
    public String toString(){
        return Foodid+" "+Foodname+" "+quantity+" "+expiryDate+" "+allocated;
    }
}
