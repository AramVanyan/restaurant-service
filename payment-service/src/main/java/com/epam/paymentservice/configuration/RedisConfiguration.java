package com.epam.paymentservice.configuration;

import com.epam.paymentservice.entity.Payment;
import com.epam.paymentservice.publisher.EventPublisher;
import com.epam.paymentservice.publisher.PaymentHistoryPublisher;
import com.epam.paymentservice.subscriber.PaymentSubscriber;
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
        container.addMessageListener(messageListener(), topic());
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new PaymentSubscriber());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("paymentChannel");
    }

    @Bean
    EventPublisher redisPublisher(@Autowired RedisTemplate<?, ?> redisTemplate) { return new EventPublisher(redisTemplate, publishTopic()); }
    @Bean
    ChannelTopic publishTopic() {
        return new ChannelTopic("sagaChannel");
    }

    @Bean
    PaymentHistoryPublisher redisHistoryPublisher(@Autowired RedisTemplate<?, ?> redisTemplate) {
        return new PaymentHistoryPublisher(redisTemplate, publishHistoryTopic());
    }
    @Bean
    ChannelTopic publishHistoryTopic() {
        return new ChannelTopic("history");
    }

}
