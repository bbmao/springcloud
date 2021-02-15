package com.guigu.springcloud.service;

import com.guigu.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment queryPaymentById(Long id);
}
