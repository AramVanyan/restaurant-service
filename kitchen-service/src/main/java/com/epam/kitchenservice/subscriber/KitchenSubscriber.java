package com.epam.kitchenservice.subscriber;

import com.epam.kitchenservice.dto.TicketDto;
import com.epam.kitchenservice.entity.Ticket;
import com.epam.kitchenservice.event.Event;
import com.epam.kitchenservice.event.EventResult;
import com.epam.kitchenservice.event.EventType;
import com.epam.kitchenservice.mapper.TicketMapper;
import com.epam.kitchenservice.service.KitchenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@NoArgsConstructor
public class KitchenSubscriber implements MessageListener {
    private KitchenService kitchenService;
    private ObjectMapper objectMapper;
    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);


    @Autowired
    public KitchenSubscriber(KitchenService kitchenService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.kitchenService = kitchenService;

    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        TicketDto ticketDto = objectMapper.readValue(message.getBody(), TicketDto.class);
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        kitchenService.save(ticket);

        Event event = Event.builder()
                .orderId(ticket.getOrderId())
                .eventType(EventType.PAYMENT)
                .eventResult(EventResult.SUCCESS)
                .build();
        kitchenService.publishEvent(event);

    }
}
