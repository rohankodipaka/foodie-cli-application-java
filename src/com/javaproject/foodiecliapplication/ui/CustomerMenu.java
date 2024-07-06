package com.javaproject.foodiecliapplication.ui;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.exceptions.CustomerNotFoundException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu extends Menu{

    private final CustomerController customerController;

    public CustomerMenu(){
        this.customerController = Factory.getCustomerController();
    }

    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO CUSTOMER SECTION");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Register (New Customer)");
                System.out.println("2. Login  (Existing Customer)");
                System.out.println("3. Display All Customers ");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");

                System.out.println("Please enter your choice (1-7)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> customerRegistrationForm();
                    case 2 -> customerLoginForm();
                    case 3 -> displayAllCustomers();
                    case 4 -> deleteCustomerForm();
                    case 5 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMainMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-7)");

                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            displayMenu();
        }
    }



    private void customerRegistrationForm(){
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
                customerRegistrationForm();
            }
        } catch (CustomerExistException e){
            System.out.println(e.getMessage());
            System.out.println("Please login using main menu");
            displayMainMenu();
        }

    }

    public void customerLoginForm(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Please login entering the following details\n");
            System.out.println("Enter E-mail");
            String email = sc.nextLine();
            System.out.println("Enter Password");
            String password = sc.nextLine();
            Customer existingCustomer = customerController.validateCustomerLogin(email, password);
            System.out.println("Login Success : ");
            System.out.println("Welcome Mr : " + existingCustomer.getName());
        } catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void displayAllCustomers() {
        List<Customer> customersList = this.customerController.getAllCustomers();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Customers");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "E-mail", "Password");
        System.out.println(dashesLine);
        customersList.forEach(customer -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", customer.getId(), customer.getName(), customer.getEmail(), "*".repeat(customer.getPassword().length()));
        });
    }

    public void deleteCustomerForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to delete the Customer\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            customerController.deleteCustomer(id);
            System.out.println("Customer Deleted Successfully");
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }




}
