package com.guigu.springcloud.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.guigu.springcloud.entities.Payment;
import com.guigu.springcloud.lb.LoadBalancer;
import com.guigu.springcloud.response.CommonResult;
import com.guigu.springcloud.response.ErrorCode;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @auther:mao
 * @create: 2021-01-03 15:55:44
 **/
@RequestMapping("/consumer")
@RestController
public class OrderController {

    public static final String payment_uri= "http://CLOUD-PAYMENT-SERVICE";
    public static final String create_uri ="/payment/create";
    public static final String getId = "/payment/get/%s";
    public static final String paymentLb = "/payment/payment/lb";
    public static final String zipkinUrl = "/payment/zipkin";

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/create")
    public CommonResult<Integer> create(Payment payment){
        String uri = String.join("", payment_uri + create_uri);
        return restTemplate.postForObject(uri, payment, CommonResult.class);
    }

    @GetMapping("/create2")
    public CommonResult<Integer> create2(Payment payment){
        String uri = String.join("", payment_uri + create_uri);
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(uri, payment, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }else {
            return ErrorCode.error.exception();
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getId(@PathVariable("id") Long id){
        String uri = String.format(payment_uri + getId, id);
        return restTemplate.getForObject(uri, CommonResult.class);
    }

    @GetMapping("/get2/{id}")
    public CommonResult<Payment> getId2(@PathVariable("id") Long id){
        String uri = String.format(payment_uri + getId, id);
        return restTemplate.getForEntity(uri, CommonResult.class).getBody();
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtil.isEmpty(instances)){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        String url= uri + paymentLb;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * zipkin+sleuth
     * @return
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        String url = "http://localhost:8001" + zipkinUrl;
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

}
