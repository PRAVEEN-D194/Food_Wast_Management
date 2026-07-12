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
    static ArrayList<Receiver> Receiverlist = new ArrayList<>();
    static ArrayList<Food> Foodlist = new ArrayList<>();
    static int receiver_id;
    static int donor_id;

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
            System.out.println("2.Doner");
            System.out.println("3.Receiver");
            System.out.println("4.Exit");
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
                    loginDoner();
                    break;

                case 3:
                    loginReceiver();
                    break;
                case 4:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }while (ch!=4);
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
            System.out.println("Enter your choice:");

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


    public static void loginDoner(){
        Scanner scanner = new Scanner(System.in);
        int c;
        do {

            System.out.println("1. login");
            System.out.println("2. signup");
            System.out.println("3. exit");
            System.out.println("Enter Your option");

            c = scanner.nextInt();
            scanner.nextLine();
            try {

                if (c == 1) {
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    String sql = "SELECT * FROM Donor WHERE email=? AND password=?";
                    PreparedStatement ps = con.prepareStatement(
                            sql
                    );
                    ps.setString(1, email);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Login Successful");
                        donor_id = rs.getInt("donor_id");
                        donorlist();
                    } else {
                        System.out.println("Invalid Email or Password");
                    }
                }
                if (c == 2) {
                    Scanner sc = new Scanner(System.in);

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    String sql = "INSERT INTO Donor(name, email, phone, password, address) VALUES(?, ?, ?, ?,  ?)";

                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    ps.setString(4, password);
                    ps.setString(5, address);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Registration Successful!");
                        ResultSet rsp = ps.executeQuery("SELECT LAST_INSERT_ID()");
                        if (rsp.next()) {
                            donor_id =  rsp.getInt(1);
                        }
                        donorlist();
                    } else {
                        System.out.println("Registration Failed!");
                    }
                }
                if (c == 3) {
                    System.out.println("exit");
                }
            }catch (Exception e){
                System.out.println("login page failed");
            }
        } while (c != 3);

    }
    public static void loginReceiver(){


            Scanner scanner = new Scanner(System.in);
            int c;
            do {

                System.out.println("1. login");
                System.out.println("2. signup");
                System.out.println("3. exit");
                System.out.println("Enter Your option");

                c = scanner.nextInt();
                scanner.nextLine();
                try {

                    if (c == 1) {
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    String sql = "SELECT * FROM receiver WHERE email=? AND password=?";
                        PreparedStatement ps = con.prepareStatement(
                                sql
                        );
                    ps.setString(1, email);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Login Successful");
                        receiver_id = rs.getInt("receiver_id");

                        System.out.println("Receiver ID = " + receiver_id);
                        receiverlist();
                    } else {
                        System.out.println("Invalid Email or Password");
                    }
                }
                if (c == 2) {
                    Scanner sc = new Scanner(System.in);

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    String sql = "INSERT INTO receiver(name, email, phone, password, location) VALUES(?, ?, ?, ?,  ?)";

                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    ps.setString(4, password);
                    ps.setString(5, address);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Registration Successful!");
                        ResultSet rsp = ps.executeQuery("SELECT LAST_INSERT_ID()");
                        if (rsp.next()) {
                           receiver_id =  rsp.getInt(1);
                        }
                        receiverlist();
                    } else {
                        System.out.println("Registration Failed!");
                    }
                }
                if (c == 3) {
                    System.out.println("exit");
                }
                }catch (Exception e){
                    System.out.println("login page failed");
                }
            } while (c != 3);


    }
    public static void donorlist(){
        Scanner scanner = new Scanner(System.in);
        int c;
//
        do{

            System.out.println("1. Add Food");
            System.out.println("2. View Avilable Food");
            System.out.println("3. LogOut");
            System.out.println("Please Enter Your Choice");

            c = scanner.nextInt();

            switch (c){
                case 1:
                    addFood();
                    break;
                case 2:
                    viewFood();
                    break;
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter valid Choice");
                    break;
            }
        }while(c!=3);
    }
    public static void receiverlist(){
        Scanner scanner = new Scanner(System.in);
        int c;
        do{

            System.out.println("1. View Avilable Food");
            System.out.println("2. Select Food");
            System.out.println("3. LogOut");
            System.out.println("Please Enter Your Choice");

            c = scanner.nextInt();

            switch (c){

                case 1:
                    viewFood();
                    break;
                case 2:
                    allocatefood();
                    break;
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter valid Choice");
                    break;
            }
        }while(c!=3);

    }


    public static void allocatefood() {
        try {
            String query = "select * from food where allocated = false and expirydate > NOW();";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            Scanner s = new Scanner(System.in);
            int i = 0;

            while (rs.next()) {
                System.out.println(
                        rs.getInt("food_id") + " --> " +
                                rs.getString("foodname") + " " +
                                rs.getInt("quantity")
                );
                i++;
            }

            if (i != 0) {

                System.out.println("Enter Food ID:");
                int foodid = s.nextInt();
                // Store these details
                String insert = "INSERT INTO food_allocation(food_id, receiver_id) VALUES(?, ?)";
                PreparedStatement p1 = con.prepareStatement(insert);
                p1.setInt(1, foodid);
                p1.setInt(2, receiver_id);
                p1.executeUpdate();

                // Mark food as allocated
                String update = "UPDATE food SET allocated = true WHERE food_id = ?";
                PreparedStatement p2 = con.prepareStatement(update);
                p2.setInt(1, foodid);

                if (p2.executeUpdate() > 0) {
                    System.out.println("Food allocated successfully.");
                }

            } else {
                System.out.println("Food Not Available");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

//            Donor d = new Donor(Donorlist.size()+1, name,address, phoneNo);
//            Donorlist.add(d);
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
                    "INSERT INTO food( foodname, quantity, expirydate, allocated, donor_id) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, foodname);
            ps.setInt(2,  Integer.parseInt(quantity));
            ps.setString(3, expiry);
            ps.setBoolean(4, false);
            ps.setInt(5, donor_id);
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

}
