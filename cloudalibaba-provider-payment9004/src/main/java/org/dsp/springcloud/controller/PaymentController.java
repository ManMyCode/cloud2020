package org.dsp.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private static HashMap<Long,Payment> hashmap = new HashMap<>();

    static {
        hashmap.put(1L,new Payment(1L,IdUtil.simpleUUID()));
        hashmap.put(2L,new Payment(2L,IdUtil.simpleUUID()));
        hashmap.put(3L,new Payment(3L,IdUtil.simpleUUID()));
    }
    @GetMapping("/paymentSQL/{id}")
    public Result<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashmap.get(id);
        Result<Payment> result = new Result<>(200,"from Mysql,ServerPort:"+serverPort,payment);
        return result;
    }
}
