package com.guigu.springcloud;

import com.guigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @auther:mao
 * @create: 2021-01-03 15:48:06
 **/
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@RibbonClient(name = "cloud-payment-service", configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
