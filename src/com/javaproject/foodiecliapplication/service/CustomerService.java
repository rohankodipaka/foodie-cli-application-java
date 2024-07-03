package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.model.Customer;

public interface CustomerService {

    public Customer save(Customer customer) throws CustomerExistException;
    //save the customer and return the customer but if customer is already exists then raise the exception created.

}
