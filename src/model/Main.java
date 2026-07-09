package model;
import com.mysql.cj.protocol.Resultset;
import db.dbconnection;
import model.Donor;
import  java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Donor> Donorlist = new ArrayList<>();
    static ArrayList<Food> Foodlist = new ArrayList<>();
    static Connection con =null;
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        int ch;
        String id;
        String password;
        try{
            con =  dbconnection.getConnection();
        }catch (Exception e){
            System.out.println("db connection failed");
        }
        do {

            System.out.println("Enter Your Roll");
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Exit");
            System.out.println(" Enter Your Choice:");

            ch = choice.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter Admin Id:");
                    id = choice.next();
                    System.out.println("Enter Admin Password:");
                    password = choice.next();
                    if(id.equals("Admin") && password.equals("12321")){
                        admin();
                    }else{
                        System.out.println("wrong id or password");
                    }
                    break;
                case 2:
                    user();
                    break;
                case 3:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }while (ch!=3);
//            switch (ch){
//                case 1:
//                    addFood();
//                    break;
//                case 2:
//                    addDonor();
//                    break;
//                case 3:
//                    viewFood();
//                    break;
//                case 4:
//                    allocatefood();
//                    break;
//                case 5:
//                    System.out.println("Manage Expired Food");
//                    break;
//                case 6:
//                    viewDonor();
//                    break;
//                case 7:
//                    System.out.println("Exit");
//                    break;
//                default:
//                    System.out.println("Enter valid Choice");
//                    break;
//
//            }
//        }while (ch != 7);
    }

    public static void admin(){
        Scanner scanner = new Scanner(System.in);
        int c;

        do {
            System.out.println("Welcome to Food Waste Management");
            System.out.println("Please Enter Your Choice");
            System.out.println("1. view Food");
            System.out.println("2. view Donor");
            System.out.println("3. Allocate Food");
            System.out.println("4. Manage Expired Food");
            System.out.println("5. logout");

            c = scanner.nextInt();

            switch (c) {
                case 1:
                    viewFood();
                    break;
                case 2:
                    viewDonor();
                    break;
                case 3:
                    allocatefood();
                    break;
                case 4:
                    System.out.println("Manage Expired Food page");
                    break;
                case 5:
                    System.out.println("logouted successfully");
                    break;
                default:
                    System.out.println("Enter valid Choice");
                    break;
            }
        }while(c!=5);
    }


    public static void user(){
        Scanner scanner = new Scanner(System.in);
        int c;
//        System.out.println("Your Have account login else signup");
//        System.out.println("1. Login");
//        System.out.println("2. Signup");
//        c=scanner.nextInt();
        do{
            System.out.println("Please Enter Your Choice");
            System.out.println("1. Add Food");
            System.out.println("2. View Avilable Food");
            System.out.println("3. Select Food");
            System.out.println("4. LogOut");

            c = scanner.nextInt();

            switch (c){
                case 1:
                    addFood();
                    break;
                case 2:
                    viewFood();
                    break;
                case 3:
                    allocatefood();
                    break;
                case 4:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter valid Choice");
                    break;
            }
        }while(c!=4);

    }
    public static void addDonor(){
        try {

            System.out.println("Add Donor Page");
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Your Name:");
            String name = s.nextLine();
            System.out.println("Enter Your Phone No:");
            String phoneNo = s.nextLine();
            System.out.println("Enter Your Address:");
            String address = s.nextLine();

            String query =
                    "INSERT INTO donor( name, address, phone) VALUES ( ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2,  address);
            ps.setInt(3, Integer.parseInt(phoneNo));
            ps.executeUpdate();
            System.out.println("Donor added successfully");

            Donor d = new Donor(Donorlist.size()+1, name,address, phoneNo);
            Donorlist.add(d);
        }catch (Exception e){
            System.out.println("donor insert failed");
        }
    }

    public static void addFood(){
        try {
            System.out.println("Add Food Page");
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Food Name:");
            String foodname = s.nextLine();
            System.out.println("Enter Food Quantity:");
            String  quantity = s.nextLine();
            System.out.println("Enter Expiry Date('YYYY-MM-DD'):");
            String expiry = s.nextLine();


            String query =
                    "INSERT INTO food( foodname, quantity, expirydate, allocated) VALUES ( ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, foodname);
            ps.setInt(2,  Integer.parseInt(quantity));
            ps.setString(3, expiry);
            ps.setBoolean(4, false);
            ps.executeUpdate();
            System.out.println("food added successfully");
//            Food f = new Food(Foodlist.size() + 1, foodname, Integer.parseInt(quantity), expiry);
//            Foodlist.add(f);
        }catch (Exception e){
            System.out.println("food add failed");
            e.printStackTrace();
        }



    }

    public static void viewDonor(){
        try {
            String query = "select * from donor";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("donor_id") + " " +
                        rs.getString("name") + " " +
                        rs.getInt("phone") + " " +
                        rs.getString("address")
                );
            }
        }catch (Exception e){
            System.out.println("view donor failed");
        }
    }
    public static void viewFood(){
        try {
            String query = "select * from food";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("food_id") + " " +
                        rs.getString("foodname") + " " +
                        rs.getInt("quantity") + " " +
                        rs.getString("expirydate") + " " +
                        rs.getBoolean("allocated")
                );
            }
        }catch (Exception e){
            System.out.println("view food failed");
        }
    }

    public static void allocatefood(){
        try {
            String query = "select * from food where allocated = false and expirydate > NOW();";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int foodid=0;
            Scanner s = new Scanner(System.in);
            int i=0;
            while (rs.next()) {
                System.out.println(
                        rs.getInt("food_id") + " -->  " +
                        rs.getString("foodname") + " " +
                        rs.getInt("quantity") + " " +
                        rs.getString("expirydate") + " " +
                        rs.getBoolean("allocated")
                );
                i++;
            }
            if(i!=0) {
                System.out.println("enter what You have");
                foodid = s.nextInt();

                String q = "update food set allocated = true where food_id = ?";

                PreparedStatement p = con.prepareStatement(q);
                p.setInt(1, foodid);
                int row = p.executeUpdate();

                if (row > 0) {
                    System.out.println("Food allocation updated successfully");
                } else {
                    System.out.println("Food allocation updated failed Retry");
                }
            }else{
                System.out.println("Food Not Avilable For Eatable");
            }

        }catch (Exception e){
            System.out.println("view food failed");
        }
    }

}
