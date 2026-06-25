package model;
import model.Donor;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Donor> Donorlist = new ArrayList<>();

    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        int ch;
        do{
            System.out.println("Welcome to Food Waste Management");
            System.out.println("Please Enter Your Choice");
            System.out.println("1. Add Food");
            System.out.println("2. Add Donor");
            System.out.println("3. View Avilable Food");
            System.out.println("4. Allocate Food");
            System.out.println("5. Manage Expired Food");
            System.out.println("6. View Donors");
            System.out.println("7. Exit");
            System.out.println(" Enter Your Choice:");
            ch = choice.nextInt();
            switch (ch){
                case 1:
                    System.out.println("add Food");
                    break;
                case 2:
                    addDonor();
                    break;
                case 3:
                    System.out.println("View Avilable Food");
                    break;
                case 4:
                    System.out.println("Allocate Food");
                    break;
                case 5:
                    System.out.println("Manage Expired Food");
                    break;
                case 6:
                    viewDonor();
                    break;
                case 7:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter valid Choice");
                    break;

            }
        }while (ch != 7);
    }

    public static void addDonor
(){
        System.out.println("Add Donor Page");
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Your Name:");
        String name = s.nextLine();
        System.out.println("Enter Your Phone No:");
        String phoneNo = s.nextLine();
        System.out.println("Enter Your Address:");
        String address = s.nextLine();
        Donor d = new Donor(Donorlist.size()+1, name,address, phoneNo);
        Donorlist.add(d);
    }

    public static void viewDonor(){
        for (Donor d:Donorlist){
            System.out.println(d.toString());
        }
    }
}
