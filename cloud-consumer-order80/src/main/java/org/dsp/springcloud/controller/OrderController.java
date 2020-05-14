package org.dsp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.dsp.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment/create")
    public Result<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Result.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Result.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public Result<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<Result> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, Result.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new Result<>(500, "操作失败");
        }
    }
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        String url = uri+"/payment/lb";
        System.out.println("url:"+url);
        return restTemplate.getForObject(url,String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/",String.class);
    }

}