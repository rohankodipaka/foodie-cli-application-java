package com.javaproject.foodiecliapplication.ui;

import com.javaproject.foodiecliapplication.controller.DishController;
import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.List;
import java.util.Scanner;

public class DishesMenu extends Menu{

    private final DishController dishController;

    public DishesMenu() {
        this.dishController = Factory.getDishController();
    }

    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                displayMenuHeader("WELCOME TO DISHES SECTION");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Add New Dish");
                System.out.println("2. View All Dish Items");
                System.out.println("3. Search Dish");
                System.out.println("4. Update Dish ");
                System.out.println("5. Delete Dish");
                System.out.println("6. Exit");

                System.out.println("Please enter your choice (1-6)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> addDishToMenu();
                    case 2 -> displayDishes();
                    case 3 -> dishSearch();
                    case 4 -> updateDishMenu();
                    case 5 -> dishDeleteFromMenu();
                    case 6 -> {
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





    private void displayDishes(){
        List<Dish> dishesList = dishController.getDishesList();
        String dashLine = new String(new char[150]).replace('\0','-');
        //displayMenuHeader("Menu Items");
        System.out.printf("%-10s %-30s %-80s %-10s \n", "Id", "Name", "Description", "Price");
        System.out.println(dashLine);
        dishesList.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s \n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        });


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
        double price = sc.nextDouble();

        Dish dish = new Dish();
        dish.setId(id)
                .setName(name)
                .setDescription(description)
                .setPrice(price);

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
                addDishToMenu();
            }
        } catch (DishAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }

    public void dishDeleteFromMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to delete the Dish\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            dishController.deleteDish(id);
            System.out.println("Dish Deleted Successfully");
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void dishSearch() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to search for Customer\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            Dish dish = dishController.getDishId(id);
            displayDish(dish);
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void displayDish(Dish dish) {
        displayMenuHeader("Dish Details");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        System.out.println();
    }

    public void updateDishMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Update entering the following details\n");
            System.out.println("Enter Dish Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);

            Dish updatedDish = dishController.update(dish);
            System.out.println("Dish Updated Successfully");
            displayDish(updatedDish);

        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            updateDishMenu();
        }
    }
}



