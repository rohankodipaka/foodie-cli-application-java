package com.javaproject.foodiecliapplication.repository;

import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.util.CsvReader;
import com.javaproject.foodiecliapplication.util.Factory;

import java.util.List;
import java.util.Optional;

public class DishRepository {

    private List<Dish> dishesList;

    public DishRepository() {
        this.dishesList = Factory.getCsvReader().readDishesFromCsv();

    }

    public List<Dish> getDishesList(){
        return this.dishesList;
    }

    public Dish save(Dish dish){
        this.dishesList.add(dish);
        return dish;
    }

    public Optional<Dish> getDishById(String id){
        return this.dishesList.stream().filter(dish -> dish.getId().equals(id)).findFirst();
    }

    public Dish updateDish(Dish dishToBeUpdated){
         Optional<Dish> dishOptional = this.dishesList.stream() .filter(dish -> dish.getId().equals(dishToBeUpdated.getId()))
                .findFirst()
                .map(dish -> {
                    dish.setName(dishToBeUpdated.getName());
                    dish.setId(dishToBeUpdated.getId());
                    dish.setDescription(dishToBeUpdated.getDescription());
                    dish.setPrice(dishToBeUpdated.getPrice());
                    return dish;
        });
         return dishOptional.orElse(null);
    }

    public void deleteDish(Dish dish){
        this.dishesList.remove(dish);
    }




}
