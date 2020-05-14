package org.dsp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.dsp.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public Result<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon,客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
