package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.RestaurantAlreadyExistException;
import com.javaproject.foodiecliapplication.exceptions.RestaurantNotFoundException;
import com.javaproject.foodiecliapplication.model.Restaurant;
import com.javaproject.foodiecliapplication.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.getAllRestaurants();
    }

    @Override
    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(restaurant.getId());
        if(restaurantById.isPresent())
            throw new RestaurantAlreadyExistException("Restaurant already exists with Id : " + restaurant.getId());
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurantTobeUpdated) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(restaurantTobeUpdated.getId());
        if(!restaurantById.isPresent())
            throw new RestaurantNotFoundException("Restaurant not found with Id" + restaurantTobeUpdated.getId());
        return this.restaurantRepository.updateRestaurant(restaurantTobeUpdated);
    }

    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(id);
        if(!restaurantById.isPresent())
            throw new RestaurantNotFoundException("Restaurant not found with Id" + id);
        return restaurantById.get();
    }

    @Override
    public void delete(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(id);
        if(restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with this Id  :" + id);
        this.restaurantRepository.deleteRestaurant(restaurantById.get());

    }
}
