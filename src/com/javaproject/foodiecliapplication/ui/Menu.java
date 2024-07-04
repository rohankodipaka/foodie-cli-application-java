package com.javaproject.foodiecliapplication.ui;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.Scanner;

public class Menu {

    public void displayMainMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){

            System.out.println("*********************************************************");
            System.out.println("                 WELCOME TO FOODIE APP                   ");
            System.out.println("*********************************************************");

            System.out.println();
            System.out.println("Please select the option !");
            System.out.println("--------------------------");
            System.out.println("1. Register (New Customer)");
            System.out.println("2. Login (Existing Customer)");
            System.out.println("3. View Restaurants");
            System.out.println("4. View Menu");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");

            System.out.println("PLease enter your choice(1-7)");

            int input = scanner.nextInt();
            switch (input){
                case 1 :
                    displayRegisterMenu();
                    break;
                case 7 :
                    System.out.println("Thank you for using Foodie App. See You Again !");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter the valid input from(1-7)");
            }

        }

        }

        private void displayRegisterMenu(){
        Scanner scanner = new Scanner(System.in);
            System.out.println("Please register entering the following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter E-mail");
            String email = scanner.nextLine();
            System.out.println("Enter Password");
            String password = scanner.nextLine();

            Customer customer = new Customer();
            customer.setId(id)
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password);

            CustomerController customerController = Factory.getCustomerController();

            try{
                Customer savedCustomer = customerController.save(customer);
                if(savedCustomer != null) {
                    System.out.println("Customer Registration Successful");
                    System.out.println("Details");
                    System.out.println("Id : " + customer.getId());
                    System.out.println("Name : " + customer.getName());
                    System.out.println("Email : " + customer.getEmail());
                    System.out.println("Password : " + customer.getPassword());
                } else{
                    System.out.println("Some internal issue occurred, please try again");
                    displayRegisterMenu();
                }
            } catch (CustomerExistException e){
                System.out.println(e.getMessage());
                System.out.println("Please login using main menu");
                displayMainMenu();
            }

    }

}
