package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.model.Dish;

public interface DishService {

    public Dish save(Dish dish) throws DishAlreadyExistsException;

}
