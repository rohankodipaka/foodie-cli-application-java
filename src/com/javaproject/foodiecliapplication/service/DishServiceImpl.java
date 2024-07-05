package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.repository.DishRepository;

import java.util.Optional;

public class DishServiceImpl implements DishService{

    private DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish save(Dish dish) throws DishAlreadyExistsException {
        Optional<Dish> dishById = this.dishRepository.getDishById(dish.getId());
        if(dishById.isPresent())
            throw new DishAlreadyExistsException("Dish already exists with Id " + dish.getId());

        return this.dishRepository.save(dish);
    }
}
