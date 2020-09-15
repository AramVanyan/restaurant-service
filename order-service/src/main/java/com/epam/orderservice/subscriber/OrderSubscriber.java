package com.epam.orderservice.subscriber;

import com.epam.orderservice.dto.DeliveryDto;
import com.epam.orderservice.dto.OrderDto;
import com.epam.orderservice.dto.PaymentDto;
import com.epam.orderservice.dto.TicketDto;
import com.epam.orderservice.entity.Order;
import com.epam.orderservice.event.Event;
import com.epam.orderservice.mapper.OrderMapper;
import com.epam.orderservice.publisher.KitchenPublisher;
import com.epam.orderservice.repository.OrderRepository;
import com.epam.orderservice.service.DeliveryService;
import com.epam.orderservice.service.KitchenService;
import com.epam.orderservice.service.OrderService;
import com.epam.orderservice.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.internal.TimestampsCacheEnabledImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@NoArgsConstructor
public class OrderSubscriber implements MessageListener {

    private PaymentService paymentService;
    private KitchenService kitchenService;
    private DeliveryService deliveryService;

    private OrderService orderService;
    private ObjectMapper objectMapper;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Autowired
    public OrderSubscriber(OrderService orderService,ObjectMapper objectMapper,PaymentService paymentService,
                           KitchenService kitchenService,DeliveryService deliveryService) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
        this.kitchenService = kitchenService;
        this.deliveryService = deliveryService;
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        OrderDto orderDto = objectMapper.readValue(message.getBody(), OrderDto.class);
        Order order = orderMapper.toEntity(orderDto);
        orderService.save(order);

        PaymentDto paymentDto = paymentService.composePayment(order);

        orderService.publishPayment(paymentDto);





    }
}
