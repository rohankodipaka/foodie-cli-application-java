package com.javaproject.foodiecliapplication.controller;


import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.exceptions.CustomerNotFoundException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {

    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    //here exception is added to method signature because in CustomerServiceImpl we need to represent the issue but not handle. Handling can be done in UI.
    public Customer save(Customer customer) throws CustomerExistException {
        return this.customerService.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    public void deleteCustomer(String id) throws CustomerNotFoundException {
        this.customerService.deleteCustomer(id);
    }

    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException{
        Customer customer = this.customerService.validateCustomerLogin(email, password);
        if(customer != null)
            this.customerService.setCurrentLoggedInCustomer(customer);
        return customer;
    }


    public Customer currentLoggedInCustomer(){
        return this.customerService.getCurrentLoggedInCustomer();
    }


}
