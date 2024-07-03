package com.javaproject.foodiecliapplication.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.javaproject.foodiecliapplication.model.Customer;

public class CsvReader {



    public List<Customer> readCustomerFromCsv(){
        String customerCsvFilePath = "D:\\workspace-java\\foodie-cli-application-java\\data\\customers.csv";
        List<Customer> customerList = new ArrayList<>();
        String line;
        //java io classes(FileReader, BufferReader)
        //try with resource
        try(BufferedReader br = new BufferedReader(new FileReader(customerCsvFilePath))){
            String CsvSplitBy = ",";
            br.readLine(); // to ignore first line --> headings

            while((line = br.readLine()) != null){
                String[] data = line.split(CsvSplitBy);
                Customer customer = new Customer();
                customer.setId(data[0])
                        .setName(data[1])
                        .setEmail(data[2])
                        .setPassword(data[3]); // Builder design pattern
                customerList.add(customer);
            }

        } catch(IOException e){
            System.out.println("File not found : " + customerCsvFilePath);
            System.exit(0);
            e.printStackTrace();
        }

        return customerList;
    }




}
