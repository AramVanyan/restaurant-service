package com.epam.deliveryservice.subscriber;

import com.epam.deliveryservice.dto.DeliveryDto;
import com.epam.deliveryservice.entity.Delivery;
import com.epam.deliveryservice.mapper.DeliveryMapper;
import com.epam.deliveryservice.repository.DeliveryRepository;
import com.epam.deliveryservice.service.DeliveryService;
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
public class DeliverySubscriber implements MessageListener {
    private DeliveryService deliveryService;
    private ObjectMapper objectMapper;
    private final DeliveryMapper deliveryMapper = Mappers.getMapper(DeliveryMapper.class);


    @Autowired
    public DeliverySubscriber(DeliveryService deliveryService,ObjectMapper objectMapper) {
        this.objectMapper=objectMapper;
        this.deliveryService = deliveryService;
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
        DeliveryDto deliveryDto = objectMapper.readValue(message.getBody(), DeliveryDto.class);
        Delivery delivery=deliveryMapper.toEntity(deliveryDto);
        deliveryService.save(delivery);






    }
}
