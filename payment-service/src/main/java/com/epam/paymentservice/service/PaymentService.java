package com.epam.paymentservice.service;


import com.epam.paymentservice.entity.Payment;

public interface PaymentService {
    Payment save(Payment payment);
    Payment compensatePayment(Long orderId);
}
