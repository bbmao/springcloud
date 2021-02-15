package com.guigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @auther:mao
 * @create: 2021-02-12 15:55:46
 **/
@Slf4j
@RestController
@RequestMapping("/payment8006")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "springcloud with consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
