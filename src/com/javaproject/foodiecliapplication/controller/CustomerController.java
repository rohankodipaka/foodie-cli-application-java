package com.javaproject.foodiecliapplication.controller;


import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.service.CustomerServiceImpl;

public class CustomerController {

    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    //here exception is added to method signature because in CustomerServiceImpl we need to represent the issue but not handle. Handling can be done in UI.
    public Customer save(Customer customer) throws CustomerExistException {
        return this.customerService.save(customer);
    }

}
