package org.dsp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Payment9004Aplication {
    public static void main(String[] args) {
        SpringApplication.run(Payment9004Aplication.class,args);
    }
}
