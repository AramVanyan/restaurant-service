package com.epam.orderservice.subscriber;

import com.epam.orderservice.entity.Delivery;
import com.epam.orderservice.entity.Payment;
import com.epam.orderservice.entity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
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

    @Override
    public void onMessage(Message message, byte[] bytes) {
        Event event = objectMapper.readValue(message.getBody(), Event.class);
        if(event.status=)
        if (event. instanceof Delivery){
            //some logic
            new RuntimeException("Delivery Error");
        }
        if (event. instanceof Payment){
            //some logic
            new RuntimeException("Payment Error");
        }
        if (event. instanceof Ticket){
            //some logic
            new RuntimeException("Kitchen error");
        }


    }
}
