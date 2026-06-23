package model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        int ch;
        do{
            System.out.println("Welcome to Food Waste Management");
            System.out.println("Please Enter Your Choice");
            System.out.println("1. Add Food");
            System.out.println("2. Add Doner");
            System.out.println("3. View Avilable Food");
            System.out.println("4. Allocate Food");
            System.out.println("5. Manage Expired Food");
            System.out.println(" Enter Your Choice:");
            ch = choice.nextInt();
            switch (ch){
                case 1:
                    System.out.println("add food");
                    break;
                case 2:
                    System.out.println("add Donar");
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
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter valid Choice");
                    break;

            }
        }while (ch != 6);
    }
}
