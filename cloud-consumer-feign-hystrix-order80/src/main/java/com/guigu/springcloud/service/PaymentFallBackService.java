package com.guigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @auther:mao
 * @create: 2021-02-13 23:56:40
 **/
@Component
public class PaymentFallBackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----paymentInfo_OK fall back";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----paymentInfo_TimeOut  fall back";
    }
}
