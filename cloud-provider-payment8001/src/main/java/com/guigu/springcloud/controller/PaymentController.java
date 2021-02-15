package com.guigu.springcloud.controller;

import com.guigu.springcloud.entities.Payment;
import com.guigu.springcloud.response.CommonResult;
import com.guigu.springcloud.response.ErrorCode;
import com.guigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther:mao
 * @create: 2021-01-02 18:04:48
 **/
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        return ErrorCode.success.response(result);
    }

    @GetMapping("/get/{id}")
    public CommonResult queryPaymentById(@PathVariable("id") Long id){
        log.info("port:[{}]", serverPort);
        return ErrorCode.success.response(paymentService.queryPaymentById(id));
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String item: services){
            log.info("****element:{}", item);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance item:instances){
            log.info("{}\t{}\t{}\t{}\t", item.getInstanceId(), item.getHost(), item.getPort(), item.getUri());
        }
        return discoveryClient;
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
