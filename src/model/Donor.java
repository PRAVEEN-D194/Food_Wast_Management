package model;


public class Donor {
    private int Donorid;
    private String name;
    private String Address;
    private String Phoneno;

    public Donor(int id,String name, String address, String Phoneno){
        Donorid=id;
        this.name = name;
        this.Address = address;
        this.Phoneno = Phoneno;

    }
    public String getDonorName(){
        return this.name;
    }
    public String getDonorAddress(){
        return this.Address;
    }
    public String getPhoneno(){
        return this.Phoneno;
    }
    public  String toString(){
        return Donorid+" "+name +" "+Address+" "+ Phoneno;
    }

}
