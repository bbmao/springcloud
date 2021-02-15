package com.guigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther:mao
 * @create: 2021-02-12 16:10:41
 **/
@RestController
 @RequestMapping("/order80")
public class OrderController {
    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/consul")
    public String paymentInfo(){
        String url = String.format("%s/%s", INVOKE_URL, "payment8006/payment/consul");
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
