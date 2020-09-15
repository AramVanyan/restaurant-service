package com.epam.deliveryservice.service.impl;

import com.epam.deliveryservice.entity.Delivery;
import com.epam.deliveryservice.repository.DeliveryRepository;
import com.epam.deliveryservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }
}
