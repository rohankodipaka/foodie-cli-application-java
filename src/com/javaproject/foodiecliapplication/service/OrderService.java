package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.orderAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.orderNotFoundException;
import com.javaproject.foodiecliapplication.model.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getOrdersList();
    public Order getOrderById(String id) throws orderNotFoundException;
    public Order save(Order order) throws orderAlreadyExistsException;

}
