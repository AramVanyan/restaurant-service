package com.epam.kitchenservice.service.impl;

import com.epam.kitchenservice.entity.Ticket;
import com.epam.kitchenservice.event.Event;
import com.epam.kitchenservice.repository.KitchenRepository;
import com.epam.kitchenservice.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    private final KitchenRepository kitchenRepository;

    @Override
    public Ticket compensateTicket(Long orderId) {
        return kitchenRepository.deleteByOrderId(orderId);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return kitchenRepository.save(ticket);
    }

    @Override
    public Event publishEvent(Event event) {
        return null;
    }
}
