package com.epam.orderservice.service;

import com.epam.orderservice.dto.DeliveryDto;
import com.epam.orderservice.dto.PaymentDto;
import com.epam.orderservice.dto.TicketDto;
import com.epam.orderservice.entity.Order;

public interface OrderService {
    Order save(Order order);
    Order compensateOrder(Long orderId);
    DeliveryDto publishDelivery(DeliveryDto deliveryDto);
    PaymentDto publishPayment(PaymentDto paymentDto);
    TicketDto publishTicket(TicketDto ticketDto);
}
