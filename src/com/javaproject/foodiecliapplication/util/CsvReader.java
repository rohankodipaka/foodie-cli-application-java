package com.javaproject.foodiecliapplication.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.javaproject.foodiecliapplication.model.Customer;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.model.Restaurant;

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


    public List<Dish> readDishesFromCsv(){

        String dishCsvFilePath = "D:\\workspace-java\\foodie-cli-application-java\\data\\dishes.csv";
        List<Dish> dishesList = new ArrayList<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(dishCsvFilePath))){
            String CsvSplitBy = ",";
            br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(CsvSplitBy);
                Dish dish = new Dish();
                dish.setId(data[0])
                        .setName(data[1])
                        .setDescription(data[2])
                        .setPrice(Double.parseDouble(data[3]));
                dishesList.add(dish);
            }

        } catch (IOException e){
            System.out.println("File not found : " + dishCsvFilePath);
            System.exit(0);
            e.printStackTrace();
        }

        return dishesList;

    }

    public List<Restaurant> readRestaurantsFromCsv(){
        String restaurantCsvFilePath = "D:\\workspace-java\\foodie-cli-application-java\\data\\restaurants.csv";
        List<Restaurant> restaurantList = new ArrayList<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(restaurantCsvFilePath))){
            String CsvSplitBy = ",";
            br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(CsvSplitBy);
                Restaurant restaurant = new Restaurant();
                restaurant.setId(data[0])
                        .setName(data[1])
                        .setAddress(data[2])
                        .setMenu(Arrays.asList(data[3].split(":")));
                restaurantList.add(restaurant);
            }

        } catch (IOException e){
            System.out.println("File not Found" + restaurantCsvFilePath);
            System.exit(0);
            e.printStackTrace();
        }
        return restaurantList;
    }




}
