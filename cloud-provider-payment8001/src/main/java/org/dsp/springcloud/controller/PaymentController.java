package org.dsp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.dsp.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public Result create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果"+result);
        if (result>0){
            return new Result(200,"插入成功,serverPort:"+serverPort,result);
        }else{
            return new Result(500,"插入失败",null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果a："+payment);
        if (payment!=null){
            return new Result(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new Result(500,"查询失败",null);
        }
    }
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    /**
     * feign超时测试
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "paymentZipkin~~~~~~~~~~~~~~~~";
    }


}
