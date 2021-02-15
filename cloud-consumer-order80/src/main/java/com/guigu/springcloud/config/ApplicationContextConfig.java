package com.guigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther:mao
 * @create: 2021-01-03 15:56:34
 **/
@Configuration
public class ApplicationContextConfig {

    /**
     * applicationConetxt.xml
     * <bean id="" class =""></>
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
