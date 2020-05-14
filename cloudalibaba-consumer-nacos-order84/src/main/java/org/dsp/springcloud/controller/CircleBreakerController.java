package org.dsp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.dsp.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 服务熔断测试
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback",fallback="handlerFallback")
    //@SentinelResource(value = "fallback",blockHandler="blockHandler")
    @SentinelResource(value = "fallback",
            fallback ="handlerFallback",
            blockHandler="blockHandler",
            exceptionsToIgnore = {//异常忽略，不走handlerfallback
                IllegalArgumentException.class
            }
    )
    public Result<Payment> fallback(@PathVariable("id") Long id){
        Result<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,Result.class,id);
        if(id>=4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。。。");
        }else if(result.getData()==null){
            throw new NullPointerException("NullPointerException,该ID没有对应的记录，空指针异常");
        }
        return result;
    }
    public Result handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new Result(444,"兜底异常handlerFallback，exception异常内容为："+e.getMessage(),payment);
    }
    public Result blockHandler(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new Result(444,"兜底异常handlerFallback，exception异常内容为："+blockException.getMessage(),payment);
    }

    //===================openFeign=====================
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public Result<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
