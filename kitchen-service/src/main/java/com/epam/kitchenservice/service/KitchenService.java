package com.epam.kitchenservice.service;

import com.epam.kitchenservice.entity.Ticket;

public interface KitchenService {
    Ticket compensateTicket(Long orderId);
    Ticket save(Ticket ticket);
}
