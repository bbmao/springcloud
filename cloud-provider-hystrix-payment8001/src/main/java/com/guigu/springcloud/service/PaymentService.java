package com.guigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.guigu.springcloud.response.ErrorCode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @auther:mao
 * @create: 2021-02-13 21:00:37
 **/
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 3;
//        int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_Timeout,id:" + id + ",耗时" + timeNumber + "秒钟";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler,id:" + id;
    }

    //----服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallBack(@PathVariable("id") Integer id){
            return "id 不能为负数，请稍后再试，id:" + id;
    }
}
