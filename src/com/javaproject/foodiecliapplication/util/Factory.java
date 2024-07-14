package com.javaproject.foodiecliapplication.util;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.controller.DishController;
import com.javaproject.foodiecliapplication.controller.OrderController;
import com.javaproject.foodiecliapplication.controller.RestaurantController;
import com.javaproject.foodiecliapplication.repository.CustomerRepository;
import com.javaproject.foodiecliapplication.repository.DishRepository;
import com.javaproject.foodiecliapplication.repository.OrderRepository;
import com.javaproject.foodiecliapplication.repository.RestaurantRepository;
import com.javaproject.foodiecliapplication.service.CustomerServiceImpl;
import com.javaproject.foodiecliapplication.service.DishServiceImpl;
import com.javaproject.foodiecliapplication.service.OrderServiceImpl;
import com.javaproject.foodiecliapplication.service.RestaurantServiceImpl;

public class Factory {

    public static CustomerRepository getCustomerRepository(){
        return new CustomerRepository();
    }

    public static CustomerServiceImpl getCustomerService(){
        return new CustomerServiceImpl(getCustomerRepository());
    }

    public static CustomerController getCustomerController(){
        return new CustomerController(getCustomerService());
    }

    public static DishRepository getDishRepository(){
        return new DishRepository();
    }

    public static DishServiceImpl getDishService(){
        return new DishServiceImpl(getDishRepository());
    }

    public static DishController getDishController(){
        return new DishController(getDishService());
    }

    public static CsvReader getCsvReader(){
        return new CsvReader();
    }

    public static RestaurantRepository getRestaurantRepository(){
        return new RestaurantRepository();
    }
    public static RestaurantServiceImpl getRestaurantService(){
        return new RestaurantServiceImpl(getRestaurantRepository());
    }
    public static RestaurantController getRestaurantController(){
        return new RestaurantController(getRestaurantService());
    }

    public static OrderRepository getOrderRepository(){
        return new OrderRepository();
    }
    public static OrderServiceImpl getOrderService(){
        return new OrderServiceImpl(getOrderRepository());
    }
    public static OrderController getOrderController(){
        return new OrderController(getOrderService());
    }

}
