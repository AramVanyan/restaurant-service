package com.epam.orderservice.subscriber;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.epam.orderservice.event.Event;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Slf4j
public class SagaEventSubscriber implements MessageListener {


    private ObjectMapper objectMapper;

    @Autowired
    public SagaEventSubscriber(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        Event event = objectMapper.readValue(message.getBody(), Event.class);

//        if (event.getEventType().equals(EventTyp))

    }
}
