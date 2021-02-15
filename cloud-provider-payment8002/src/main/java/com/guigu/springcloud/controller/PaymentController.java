package com.guigu.springcloud.controller;

import com.guigu.springcloud.entities.Payment;
import com.guigu.springcloud.response.CommonResult;
import com.guigu.springcloud.response.ErrorCode;
import com.guigu.springcloud.service.PaymentService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @auther:mao
 * @create: 2021-01-02 18:04:48
 **/
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        return ErrorCode.success.response(result);
    }

    @GetMapping("get/{id}")
    public CommonResult queryPaymentById(@PathVariable("id") Long id){
        log.info("port:[{}]", serverPort);
        return ErrorCode.success.response(paymentService.queryPaymentById(id));
    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
