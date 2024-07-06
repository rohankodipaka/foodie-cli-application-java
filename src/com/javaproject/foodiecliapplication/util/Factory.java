package com.javaproject.foodiecliapplication.util;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.controller.DishController;
import com.javaproject.foodiecliapplication.repository.CustomerRepository;
import com.javaproject.foodiecliapplication.repository.DishRepository;
import com.javaproject.foodiecliapplication.service.CustomerServiceImpl;
import com.javaproject.foodiecliapplication.service.DishServiceImpl;

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

}
