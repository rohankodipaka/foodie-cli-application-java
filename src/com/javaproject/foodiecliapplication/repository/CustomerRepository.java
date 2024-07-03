package com.javaproject.foodiecliapplication.repository;

import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.util.CsvReader;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private List<Customer> customerList;

    public CustomerRepository() {
        CsvReader csvReader = new CsvReader();
        this.customerList = csvReader.readCustomerFromCsv();
    }

    public List<Customer> getAllCustomers(){
        //just like select * from customers --> from db
        return this.customerList;
    }

    public Customer save(Customer customer){
        this.customerList.add(customer);
        return customer;
    }

    //To check the presence of resources (in this case, a customer), use Optional in the repository layer. If we do not use Optional for a customer not present in the CSV, we will encounter a null pointer exception.
    public Optional<Customer> findCustomerById(String id){
       return this.customerList.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }



}
