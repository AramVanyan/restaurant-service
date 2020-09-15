package com.epam.paymentservice.service.impl;

import com.epam.paymentservice.entity.Payment;
import com.epam.paymentservice.repository.PaymentRepository;
import com.epam.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
       return paymentRepository.save(payment);
    }

    @Override
    public Payment compensatePayment(Long orderId) {
        return paymentRepository.deletePaymentByOrderId(orderId);
    }
}
