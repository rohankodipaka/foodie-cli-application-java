package com.javaproject.foodiecliapplication.repository;

import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.util.CsvReader;

import java.util.List;
import java.util.Optional;

public class DishRepository {

    private List<Dish> dishesList;

    public DishRepository() {
        CsvReader csvReader = new CsvReader();
        this.dishesList = csvReader.readDishesFromCsv();
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

    public Optional<Dish> updateDish(Dish dishToBeUpdated){
        return this.dishesList.stream() .filter(dish -> dish.getId().equals(dishToBeUpdated.getId()))
                .findFirst()
                .map(dish -> {
                    dish.setName(dishToBeUpdated.getName());
                    dish.setId(dishToBeUpdated.getId());
                    dish.setDescription(dishToBeUpdated.getDescription());
                    dish.setPrice(dishToBeUpdated.getPrice());
                    return dish;
        });
    }

    public Dish deleteDish(Dish dish){
        dishesList.remove(dish);
        return dish;
    }




}
