package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;

import java.util.List;

public interface DishService {


    public List<Dish> getDishesList();
    public Dish save(Dish dish) throws DishAlreadyExistsException;
    public Dish getDishById(String id) throws DishNotFoundException;
    public Dish updateDish(Dish dishToBeUpdated) throws DishNotFoundException;
    public void delete(String id) throws DishNotFoundException;
}
