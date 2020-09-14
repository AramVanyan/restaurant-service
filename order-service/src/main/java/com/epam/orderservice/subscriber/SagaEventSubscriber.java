package com.epam.orderservice.subscriber;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Slf4j
public class SagaEventSubscriber implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {

    }
}
