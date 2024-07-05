package com.javaproject.foodiecliapplication.controller;

import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.service.DishServiceImpl;

public class DishController {

    public DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService){
        this.dishService = dishService;
    }

    public Dish save(Dish dish) throws DishAlreadyExistsException{
        return this.dishService.save(dish);
    }

}
