package com.epam.orderservice.service.impl;

import com.epam.orderservice.dto.TicketDto;
import com.epam.orderservice.entity.Order;
import com.epam.orderservice.service.KitchenService;
import org.springframework.stereotype.Service;

@Service
public class KitchenServiceImpl implements KitchenService {
    @Override
    public TicketDto composeDelivery(Order order) {
        return new TicketDto(order.getId());
    }
}
