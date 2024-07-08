package com.javaproject.foodiecliapplication.controller;


import com.javaproject.foodiecliapplication.exceptions.RestaurantAlreadyExistException;
import com.javaproject.foodiecliapplication.exceptions.RestaurantNotFoundException;
import com.javaproject.foodiecliapplication.model.Restaurant;
import com.javaproject.foodiecliapplication.service.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {

    private RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService){
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> getRestaurants(){
        return this.restaurantService.getAllRestaurants();
    }

    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistException {
        return this.restaurantService.save(restaurant);
    }

    public Restaurant update(Restaurant restaurantToBeUpdated) throws RestaurantNotFoundException{
        return this.restaurantService.update(restaurantToBeUpdated);
    }

    public Restaurant getRestaurantId(String id) throws RestaurantNotFoundException{
        return this.restaurantService.getRestaurantById(id);
    }

    public void deleteRestaurant(String id) throws RestaurantNotFoundException{
        this.restaurantService.delete(id);
    }



}
