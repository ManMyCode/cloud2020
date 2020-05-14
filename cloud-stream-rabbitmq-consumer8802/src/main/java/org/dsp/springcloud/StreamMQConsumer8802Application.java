package org.dsp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StreamMQConsumer8802Application {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQConsumer8802Application.class,args);
    }
}
