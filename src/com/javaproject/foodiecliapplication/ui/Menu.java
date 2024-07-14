package com.javaproject.foodiecliapplication.ui;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.controller.DishController;
import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.model.Restaurant;
import com.javaproject.foodiecliapplication.repository.DishRepository;
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
            System.out.println("1. Customer Section");
            System.out.println("2. Restaurant Section");
            System.out.println("3. Dishes Section");
            System.out.println("4. Order Section ");
            System.out.println("5. Exit");

            System.out.println("PLease enter your choice(1-7)");

            int input = scanner.nextInt();
            switch (input){
                case 1 -> new CustomerMenu().displayMenu();
                case 2 -> new RestaurantsMenu().displayMenu();
                case 3 -> new DishesMenu().displayMenu();
                case 4 -> new OrdersMenu().displayMenu();
                case 5 -> {
                    System.out.println("Thank you for using Foodie App. See You Again !");
                    System.exit(0);
                }
                default ->
                    System.out.println("Invalid input. Please enter the valid input from(1-7)");
            }

        }

        }

    public void displayMenuHeader(String menuHeader){
        printDashLine();
        String spaces = new String(new char[70]).replace('\0',' ');
        System.out.printf("%-70s %-10s %-70s \n", spaces, menuHeader, spaces);
        printDashLine();
    }

    public void printDashLine(){
        String dashLine = new String(new char[150]).replace('\0','-');
        System.out.println(dashLine);
    }

}
