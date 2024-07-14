package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.CustomerExistException;
import com.javaproject.foodiecliapplication.exceptions.CustomerNotFoundException;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{


    //CustomerRepository dependency is added here.
    private CustomerRepository customerRepository;

    private Customer currentLoggedInCustomer;

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

    @Override
    public List<Customer> getAllCustomers() {
      return this.customerRepository.getAllCustomers();
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Optional<Customer> customerById = this.customerRepository.findCustomerById(id);
        if(!customerById.isPresent())
            throw new CustomerNotFoundException("Customer Not Found with Id : " + id);
        this.customerRepository.deleteCustomer(customerById.get());
    }

    @Override
    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException {
        Optional<Customer> byEmailAndPassword = this.customerRepository.findByEmailAndPassword(email, password);
        if(byEmailAndPassword.isEmpty())
            throw new CustomerNotFoundException("Invalid email and password");
        return byEmailAndPassword.get();
    }


    @Override
    public void setCurrentLoggedInCustomer(Customer customer) {
        this.currentLoggedInCustomer = customer;
    }

    @Override
    public Customer getCurrentLoggedInCustomer() {
        return this.currentLoggedInCustomer;
    }



}
