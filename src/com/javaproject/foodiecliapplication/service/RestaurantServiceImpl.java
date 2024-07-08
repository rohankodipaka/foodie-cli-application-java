package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.exceptions.RestaurantAlreadyExistException;
import com.javaproject.foodiecliapplication.exceptions.RestaurantNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.model.Restaurant;
import com.javaproject.foodiecliapplication.repository.RestaurantRepository;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.ArrayList;
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

    @Override
    public List<Dish> getDishItems(String id) throws RestaurantNotFoundException, DishNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(id);
        if(restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not found" + id);
        Restaurant restaurant = restaurantById.get();
        List<Dish> dishes = new ArrayList<>();
        List<String> dishIds = restaurant.getMenu();
        DishService dishService= Factory.getDishService();
        for(String dishId : dishIds){
            Dish dish = dishService.getDishById(dishId);
            dishes.add(dish);
        }
        return dishes;
    }
}
