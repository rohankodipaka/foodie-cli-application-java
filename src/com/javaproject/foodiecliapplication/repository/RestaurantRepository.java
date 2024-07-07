package com.javaproject.foodiecliapplication.repository;

import com.javaproject.foodiecliapplication.model.Restaurant;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository {

    private List<Restaurant> restaurantList;

    public RestaurantRepository(){
        this.restaurantList = Factory.getCsvReader().readRestaurantsFromCsv();
    }

    public List<Restaurant> getAllRestaurants(){
        return this.restaurantList;
    }

    public Optional<Restaurant> getRestaurantById(String id){
        return this.restaurantList.stream().filter(restaurant -> restaurant.getId().equals(id)).findFirst();
    }

    public Restaurant save(Restaurant restaurant){
        this.restaurantList.add(restaurant);
        return restaurant;
    }

    public Restaurant updateRestaurant(Restaurant restaurantToBeUpdated){
        Optional<Restaurant> optionalRestaurant = this.restaurantList.stream().filter(restaurant -> restaurant.getId().equals(restaurantToBeUpdated.getId())).findFirst()
                .map(restaurant ->
                {
                    restaurant.setId(restaurantToBeUpdated.getId());
                    restaurant.setName(restaurantToBeUpdated.getName());
                    restaurant.setAddress(restaurantToBeUpdated.getAddress());
                    restaurant.setMenu(restaurantToBeUpdated.getMenu());
                    return restaurant;
                });
        return optionalRestaurant.orElse(null);
    }

    public void deleteRestaurant(Restaurant restaurant){
        this.restaurantList.remove(restaurant);
    }


}
