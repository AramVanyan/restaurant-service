package com.epam.orderservice.service;

import com.epam.orderservice.dto.DeliveryDto;
import com.epam.orderservice.dto.TicketDto;
import com.epam.orderservice.entity.Order;

public interface KitchenService {
    TicketDto composeDelivery(Order order);
}