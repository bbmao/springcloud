package com.guigu.springcloud.controller;

import com.guigu.springcloud.entities.Payment;
import com.guigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @auther:mao
 * @create: 2021-02-13 22:39:28
 **/
@Slf4j
@RestController
@DefaultProperties(defaultFallback = "payment_global_fallBackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result  = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    public String paymentInfo_Timeout(Integer id){
        int age = 10/0;
        String result  = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试试或者自己运行出错请检查自己";
    }

    /**
     * 全局的兜底方案
     * @param id
     * @return
     */
    public String payment_global_fallBackMethod(){
        return "global异常处理信息，请稍后再试。";
    }

}
