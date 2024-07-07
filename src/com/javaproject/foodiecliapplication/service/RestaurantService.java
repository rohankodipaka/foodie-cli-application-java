package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.RestaurantAlreadyExistException;
import com.javaproject.foodiecliapplication.exceptions.RestaurantNotFoundException;
import com.javaproject.foodiecliapplication.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> getAllRestaurants();
    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistException;
    public Restaurant update(Restaurant restaurantTobeUpdated) throws RestaurantNotFoundException;
    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException;
    public void delete(String id) throws RestaurantNotFoundException;

}
