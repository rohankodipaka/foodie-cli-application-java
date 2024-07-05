package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.DishAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.DishNotFoundException;
import com.javaproject.foodiecliapplication.model.Dish;
import com.javaproject.foodiecliapplication.repository.DishRepository;

import java.util.List;
import java.util.Optional;

public class DishServiceImpl implements DishService{

    private DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }


    @Override
    public List<Dish> getDishesList() {
        return this.dishRepository.getDishesList();
    }

    @Override
    public Dish getDishById(String id) throws DishNotFoundException {
        Optional<Dish> dishById = this.dishRepository.getDishById(id);
        if(!dishById.isPresent())
            throw new DishNotFoundException("Dish not found with Id : " +id);
        return dishById.get();
    }

    @Override
    public Dish save(Dish dish) throws DishAlreadyExistsException {
        Optional<Dish> dishById = this.dishRepository.getDishById(dish.getId());
        if(dishById.isPresent())
            throw new DishAlreadyExistsException("Dish already exists with Id " + dish.getId());

        return this.dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Dish dishToBeUpdated) throws DishNotFoundException {
        Optional<Dish> dishById = this.dishRepository.getDishById(dishToBeUpdated.getId());
        if(!dishById.isPresent())
            throw new DishNotFoundException("Dish not found with Id : " +dishToBeUpdated.getId());
        return this.dishRepository.save(dishToBeUpdated);
    }

    @Override
    public Dish deleteDish(Dish dish) {
        return this.dishRepository.deleteDish(dish);
    }
}
