package com.javaproject.foodiecliapplication.controller;

import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.service.DishServiceImpl;

import java.util.List;

public class DishController {

    public DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService){
        this.dishService = dishService;
    }


    public List<Dish> getDishesList(){
        return this.dishService.getDishesList();
    }

    public Dish save(Dish dish) throws DishAlreadyExistsException{
        return this.dishService.save(dish);
    }

    public Dish getDishId(String Id) throws DishNotFoundException {
        return this.dishService.getDishById(Id);
    }

    public Dish update(Dish dish) throws DishNotFoundException{
        return this.dishService.updateDish(dish);
    }

    public void deleteDish(String id) throws DishNotFoundException{
        this.dishService.delete(id);
    }


}
