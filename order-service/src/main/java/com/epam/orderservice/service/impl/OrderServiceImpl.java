package com.epam.orderservice.service.impl;

import com.epam.orderservice.entity.Order;
import com.epam.orderservice.repository.OrderRepository;
import com.epam.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order compensateOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        orderRepository.deleteById(orderId);
        return order;
    }
}
