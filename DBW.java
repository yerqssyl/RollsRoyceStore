package com.company;

import java.util.Scanner;
import java.util.InputMismatchException;

public class DBW {
    private  OrderControl controller;
    private  Scanner input;

    public DBW(OrderControl controller) {
        this.controller = controller;
        input = new Scanner(System.in); //Scanner (input)
    }

    public void start() {
        while (true) {
            System.out.println("Select your option:\n" +
                    "1: Order RR\n" + "2: Orders Archive\n" + "3: Exit.");// List
            try {
                System.out.println("Enter your option(1-3): ");//ask to choose what to do
                int choice = input.nextInt();
                if (choice == 1) {//Purchase RR
                    option1();
                } else if (choice == 2) {//See order archive (what you ordered)
                    option2();
                } else {//Exit
                    break;
                }
            } catch (InputMismatchException e) {//error
                System.out.println("Please choose integer from 1 to 3,check list");
                input.nextLine();// try again input
            } catch (Exception e) {//error
                System.out.println(e);
            }

        }
    }

    public void option1() {//Purchase products
        System.out.println("Enter your name: ");//ask to enter customer_name
        String customer_name = input.next();
        System.out.println("Enter your phone number: ");// ask to enter customer_number
        String customer_number = input.next();
        controller.createUser(customer_name, customer_number);// create user in sql
        System.out.println(controller.getProductList());
        System.out.println("Enter id number of the product that you want to purchase: ");//ask to enter car's id
        int id = input.nextInt();
        System.out.println(controller.getColor());
        System.out.println("Write color that you want to choose:");
        String color = input.next(); // enter color
        controller.purchaseProduct(customer_name, customer_number, id, color);//enter in sql information
        System.out.println("Do you want to confirm your order? (Yes or No)");// conform customer request
        String confirm = input.next();
        if (confirm.equals("Yes")) {
            System.out.println("You have successfully placed an order!");
        }
    }

    public void option2() {// See my orders
        System.out.println("Enter customer's name that you want to receive details: ");// ask to enter customer_name
        String customer_name = input.next();
        System.out.println("Enter customer's phone number that you want to receive details: ");//ask to enter customer number
        String customer_number= input.next();
        controller.getOrderDetails(customer_name, customer_number);//getter to see order
    }
}