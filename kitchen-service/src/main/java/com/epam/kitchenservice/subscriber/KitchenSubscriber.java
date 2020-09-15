package com.epam.kitchenservice.subscriber;

import com.epam.kitchenservice.dto.TicketDto;
import com.epam.kitchenservice.entity.Ticket;
import com.epam.kitchenservice.mapper.TicketMapper;
import com.epam.kitchenservice.repository.KitchenRepository;
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
    private KitchenRepository kitchenRepository;
    private ObjectMapper objectMapper;
    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);


    @Autowired
    public KitchenSubscriber(KitchenRepository kitchenRepository,ObjectMapper objectMapper) {
        this.objectMapper=objectMapper;
        this.kitchenRepository = kitchenRepository;
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        TicketDto ticketDto = objectMapper.readValue(message.getBody(), TicketDto.class);
        Ticket ticket= ticketMapper.toEntity(ticketDto);
        kitchenRepository.save(ticket);




    }
}
