package com.javaproject.foodiecliapplication.ui;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.controller.DishController;
import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.List;
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
            System.out.println("4. View Dish Menu");
            System.out.println("5. Add Dish to Menu");
            System.out.println("6. Place Order");
            System.out.println("7. View Orders");
            System.out.println("8. Exit");

            System.out.println("PLease enter your choice(1-7)");

            int input = scanner.nextInt();
            switch (input){
                case 1 :
                    displayRegisterMenu();
                    break;
                case 4 :
                    displayDishesList();
                    break;
                case 5 :
                    addDishToMenu();
                    break;
                case 8 :
                    System.out.println("Thank you for using Foodie App. See You Again !");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter the valid input from(1-7)");
            }

        }

        }


        private void displayDishesList(){
            DishController dishController = Factory.getDishController();
            //System.out.println(dishController.getDishesList());
            List<Dish> dishesList = dishController.getDishesList();
            String dashLine = new String(new char[150]).replace('\0','-');
            displayMenuHeader("Menu Items");
            System.out.printf("%-10s %-30s %-80s %-10s \n", "Id", "Name", "Description", "Price");
            System.out.println(dashLine);
            dishesList.forEach(dish -> {
                System.out.printf("%-10s %-30s %-80s %-10s \n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
            });


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

    private void addDishToMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please add Dish using following details : \n");
        System.out.println("Enter Dish Id : ");
        String id = sc.nextLine();
        System.out.println("Enter Dish name : ");
        String name = sc.nextLine();
        System.out.println("Enter Dish description : ");
        String description = sc.nextLine();
        System.out.println("Enter Dish price : ");
        String price = sc.nextLine();

        Dish dish = new Dish();
        dish.setId(id)
                .setName(name)
                .setDescription(description)
                .setPrice(Double.parseDouble(price));

        DishController dishController = Factory.getDishController();

        try{
            Dish savedDish = dishController.save(dish);
            if(savedDish != null){
                System.out.println("Dish Added Successfully");
                System.out.println("Details");
                System.out.println("Id : " + dish.getId());
                System.out.println("Name : " + dish.getName());
                System.out.println("Email : " + dish.getDescription());
                System.out.println("Password : " + dish.getPrice());
            } else{
                System.out.println("Some internal issue occurred, please try again");
                displayRegisterMenu();
            }
        } catch (DishAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }


    public void displayMenuHeader(String menuHeader){
        String dashLine = new String(new char[150]).replace('\0','-');
        System.out.println(dashLine);
        String spaces = new String(new char[70]).replace('\0',' ');
        System.out.printf("%-70s %-10s %-70s \n", spaces, menuHeader, spaces);
        System.out.println(dashLine);
    }

}
