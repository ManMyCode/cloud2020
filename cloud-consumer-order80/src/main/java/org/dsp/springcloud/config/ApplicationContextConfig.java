package org.dsp.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced//自定义负载均衡规则时要注释掉此注解
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
