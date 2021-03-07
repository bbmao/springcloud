package com.guigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther:mao
 * @create: 2021-02-18 15:20:29
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayment9002 {

    public static void main(String[] args) {
        SpringApplication.run(NacosPayment9002.class, args);
    }
}
