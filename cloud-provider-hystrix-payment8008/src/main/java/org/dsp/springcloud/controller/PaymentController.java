package org.dsp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfoOk(id);
        log.info("*****result:"+result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfoTimeout(id);
        log.info("*****result:"+result);
        return result;
    }
    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCirCuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCirCuitBreaker(id);
        log.info("***result:"+result);
        return result;
    }
}
