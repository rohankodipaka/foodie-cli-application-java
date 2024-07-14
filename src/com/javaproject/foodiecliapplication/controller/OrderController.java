package com.javaproject.foodiecliapplication.controller;

import com.javaproject.foodiecliapplication.exceptions.orderAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.orderNotFoundException;
import com.javaproject.foodiecliapplication.model.Order;
import com.javaproject.foodiecliapplication.service.OrderServiceImpl;

import java.util.List;

public class OrderController {

    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }


    public List<Order> getOrdersList(){
        return this.orderService.getOrdersList();
    }

    public Order getOrderById(String id) throws orderNotFoundException {
        return this.orderService.getOrderById(id);
    }

    public Order saveOrder(Order order) throws orderAlreadyExistsException {
        return this.orderService.save(order);
    }


}
