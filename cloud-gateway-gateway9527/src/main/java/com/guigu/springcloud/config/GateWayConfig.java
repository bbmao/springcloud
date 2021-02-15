package com.guigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther:mao
 * @create: 2021-02-14 23:49:13
 **/
@Configuration
public class GateWayConfig {

    @Bean
    @LoadBalanced
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes().route("path_route_guigu",
                r->r.path("/guonei")
                    .uri("http://news.baidu.com/guonei")).build();
    }
}
