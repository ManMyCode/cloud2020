package org.dsp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class,args);
    }

    /**
     * SpringCloud的Config Client端时，当尝试去配置文件里拿某${xxx}的值,当在第一个application.yml下找不到的时候,配置一下代码会忽略暂时在该配置文件里未取到的value
     * @return
     */
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return placeholderConfigurer;
    }*/
}
