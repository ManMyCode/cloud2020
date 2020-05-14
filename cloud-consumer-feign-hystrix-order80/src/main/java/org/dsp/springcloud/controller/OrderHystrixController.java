package org.dsp.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")//默认全局fallback
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentOk(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    public String paymentTimeout(@PathVariable("id") Integer id){
        int num = 10/0;//测试异常快速失败
        return paymentHystrixService.paymentTimeout(id);
    }
    public String paymentInfoTimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者,对方系统繁忙或出现内部错误";
    }
    //全局fallback
    public String paymentGlobalFallbackMethod(){
        return "全局异常处理信息";
    }
}
