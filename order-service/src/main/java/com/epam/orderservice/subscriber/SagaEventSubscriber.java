package com.epam.orderservice.subscriber;

import com.epam.orderservice.dto.OrderDto;
import com.epam.orderservice.event.Event;
import com.epam.orderservice.mapper.OrderMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class SagaEventSubscriber implements MessageListener {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        Event event = objectMapper.readValue(message.getBody(), Event.class);

        if (event.getEventType().equals(EventTyp))

    }
}
