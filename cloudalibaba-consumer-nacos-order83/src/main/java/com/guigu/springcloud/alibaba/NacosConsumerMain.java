package com.guigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther:mao
 * @create: 2021-02-18 15:42:00
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerMain {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain.class, args);
    }
}
