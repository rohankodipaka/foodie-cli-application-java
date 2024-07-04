package com.javaproject.foodiecliapplication.util;

import com.javaproject.foodiecliapplication.controller.CustomerController;
import com.javaproject.foodiecliapplication.repository.CustomerRepository;
import com.javaproject.foodiecliapplication.service.CustomerServiceImpl;

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

}
