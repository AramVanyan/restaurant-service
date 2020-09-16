package com.epam.orderservice.configuration;

import com.epam.orderservice.publisher.DeliveryPublisher;
import com.epam.orderservice.subscriber.OrderSubscriber;
import com.epam.orderservice.subscriber.SagaEventSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisMessageListenerContainer container() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.addMessageListener(messageListener(),topic());
        container.addMessageListener(messageListenerSaga(),responseTopic());
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new OrderSubscriber());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("createOrder");
    }


    @Bean
    MessageListenerAdapter messageListenerSaga() {
        return new MessageListenerAdapter(new SagaEventSubscriber());
    }

    @Bean
    ChannelTopic responseTopic() {
        return new ChannelTopic("orderResponse");
    }

    @Bean
    DeliveryPublisher paymentPublisher(@Autowired RedisTemplate<?, ?> redisTemplate) {
        return new DeliveryPublisher(redisTemplate, paymentTopic());
    }
    @Bean
    ChannelTopic paymentTopic() {
        return new ChannelTopic("paymentChannel");
    }

    @Bean
    DeliveryPublisher kitchenPublisher(@Autowired RedisTemplate<?, ?> redisTemplate) {
        return new DeliveryPublisher(redisTemplate, kitchenTopic());
    }
    @Bean
    ChannelTopic kitchenTopic() {
        return new ChannelTopic("kitchenChannel");
    }
}

