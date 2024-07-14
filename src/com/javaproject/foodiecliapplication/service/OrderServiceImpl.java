package com.javaproject.foodiecliapplication.service;

import com.javaproject.foodiecliapplication.exceptions.orderAlreadyExistsException;
import com.javaproject.foodiecliapplication.exceptions.orderNotFoundException;
import com.javaproject.foodiecliapplication.model.Order;
import com.javaproject.foodiecliapplication.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> getOrdersList() {
        return this.orderRepository.getOrderList();
    }

    @Override
    public Order getOrderById(String id) throws orderNotFoundException {
        Optional<Order> orderById = this.orderRepository.getOrderById(id);
        if(orderById.isEmpty())
            throw new orderNotFoundException("Order not found with Id : " + id);
        return orderById.get();
    }

    @Override
    public Order save(Order order) throws orderAlreadyExistsException {
        Optional<Order> orderById = this.orderRepository.getOrderById(order.getId());
        if(orderById.isPresent())
            throw new orderAlreadyExistsException("Order Already Exists with this Id : " + order.getId());
        return this.orderRepository.save(order);
    }
}
