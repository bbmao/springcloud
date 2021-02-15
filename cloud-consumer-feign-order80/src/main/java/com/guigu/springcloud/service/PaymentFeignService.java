package com.guigu.springcloud.service;

import com.guigu.springcloud.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult queryPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/payment/feign/timeout")
    String paymentFeignTimeOut();
}
