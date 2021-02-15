package com.guigu.springcloud.service.impl;

import com.guigu.springcloud.dao.PaymentDao;
import com.guigu.springcloud.entities.Payment;
import com.guigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther:mao
 * @create: 2021-01-02 18:00:35
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryPaymentById(Long id) {
        return paymentDao.queryPaymentById(id);
    }
}
