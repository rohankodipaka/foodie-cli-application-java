package com.javaproject.foodiecliapplication.ui;

import com.javaproject.foodiecliapplication.controller.OrderController;
import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.exceptions.RestaurantNotFoundException;
import com.javaproject.foodiecliapplication.exceptions.orderAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.orderNotFoundException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.model.Order;
import com.javaproject.foodiecliapplication.model.Restaurant;
import com.javaproject.foodiecliapplication.service.CustomerService;
import com.javaproject.foodiecliapplication.service.DishService;
import com.javaproject.foodiecliapplication.service.RestaurantService;
import com.javaproject.foodiecliapplication.util.Factory;
import com.javaproject.foodiecliapplication.model.Dish;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrdersMenu extends Menu{


    private final OrderController orderController;

    public OrdersMenu() {
        this.orderController = Factory.getOrderController();
    }

    public void displayMenu(){
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO ORDERS SECTION");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Place New Order");
                System.out.println("2. Search Order");
                System.out.println("3. View All Orders");
                System.out.println("4. Exit");
                System.out.println("Please enter your choice (1-4)");
                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> placeNewOrder();
                    case 2 -> searchOrder();
                    case 3 -> ordersList();
                    case 4 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMainMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-4)");
                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            displayMenu();
        }

    }

    private void ordersList(){
        List<Order> ordersList = this.orderController.getOrdersList();
        displayMenuHeader("All Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items", "Order Date", "Price");
        printDashLine();

        ordersList.forEach(order -> {
            String dishNames = order.getDishes().stream().map(Dish::getName).collect(Collectors.joining(","));
            System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n", order.getId(), order.getCustomer().getName(), order.getRestaurant().getName(), dishNames, order.getOrderDate(), order.getPrice());
        });
        System.out.println("\n\n");
    }



    private void searchOrder() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to search for Order\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            Order order = orderController.getOrderById(id);
            displayOrderDetails(order);
        } catch (orderNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    private void displayOrderDetails(Order order) {
        String dishNames = order.getDishes().stream().map(Dish::getName).collect(Collectors.joining(","));
        displayMenuHeader("Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items","Order Date","Price");
        printDashLine();
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n", order.getId(), order.getCustomer().getName(), order.getRestaurant().getName(), dishNames,order.getOrderDate(),String.format("$%.2f", order.getPrice()));


    }

    private void placeNewOrder() throws DishNotFoundException {

        Customer loggedInCustomer = null;
        Restaurant restaurant = null;
        List<Dish> dishList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(System.in);
            CustomerService customerService = Factory.getCustomerService();
            RestaurantService restaurantService = Factory.getRestaurantService();
            DishService dishService = Factory.getDishService();

            loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            if(loggedInCustomer != null )
                System.out.println("Hello , " + loggedInCustomer.getName());

            while (loggedInCustomer == null) {
                System.out.println("Please login to place an order");
                new CustomerMenu().customerLoginForm();
                loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            }

            System.out.println("Enter Order Id :");
            String id = scanner.nextLine();

            while (restaurant == null) {
                new RestaurantsMenu().displayRestaurants();
                printDashLine();
                System.out.println("Choose the Restaurant Id (Ex: R08 )");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(restaurantId);
            }
            char addMoreItems = 'Y';
            while (addMoreItems == 'Y') {
                new RestaurantsMenu().displayMenuItems(restaurant.getId());
                printDashLine();
                System.out.println("Enter the Dish Id (Ex : D001 )");
                String dishId = scanner.nextLine();
                Dish selectedDish = dishService.getDishById(dishId);
                dishList.add(selectedDish);
                System.out.println("One Dish is added successfully : " + selectedDish.getName());
                System.out.println("Do you want to add more dishes (Y/N)");
                addMoreItems = scanner.nextLine().charAt(0);
            }


            double orderPrice = calculateOrderTotalPrice(dishList);
            LocalDate orderDate = LocalDate.now();



            Order order = new Order();
            order.setId(id)
                    .setCustomer(loggedInCustomer)
                    .setRestaurant(restaurant)
                    .setDishes(dishList)
                    .setPrice(orderPrice)
                    .setOrderDate(orderDate);

            Order placedOrder = orderController.saveOrder(order);
            if(placedOrder != null)
                System.out.println("Order Placed Successfully with the following details");

            displayOrderDetails(placedOrder);

        } catch (RestaurantNotFoundException | orderAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private double calculateOrderTotalPrice(List<Dish> dishList) {
        return dishList.stream().mapToDouble(Dish::getPrice).sum();
    }





}
