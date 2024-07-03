package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.repository.CustomerRepository;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{


    //CustomerRepository dependency is added here.
    private CustomerRepository customerRepository;

    //create constructor to avail the customerRepository object
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws CustomerExistException {
        //if customer already exists --> check with id because they are unique. Then throw CustomerExistException
        //else save the customer in the repository
        Optional<Customer> customerById = this.customerRepository.findCustomerById(customer.getId()); //ctrl alt v
        if(customerById.isPresent())
            throw new CustomerExistException("Customer already exists with this ID : " + customer.getId());

        return this.customerRepository.save(customer);
    }

}
