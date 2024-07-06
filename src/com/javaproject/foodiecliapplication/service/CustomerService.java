package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.exceptions.CustomerNotFoundException;
import com.javaproject.foodiecliapplication.model.Customer;

import java.util.List;

public interface CustomerService {


    public List<Customer> getAllCustomers();
    public Customer save(Customer customer) throws CustomerExistException;
    //save the customer and return the customer but if customer is already exists then raise the exception created.
    public void deleteCustomer(String id) throws CustomerNotFoundException;

    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException;


}
