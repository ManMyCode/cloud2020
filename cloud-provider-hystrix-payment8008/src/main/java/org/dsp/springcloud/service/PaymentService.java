package org.dsp.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfoOk(Integer id){
        return "线程池:"+Thread.currentThread().getName()+" paymentInfoOk,id: "+id+"\t"+"^_^";
    }

    /**
     * 超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfoTimeout(Integer id){
        //暂停几秒钟线程
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+" paymentInfoTimeout,id: "+id+"\t"+"^_^,耗时"+timeNumber+"秒钟";
    }
    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+" 系统繁忙或运行报错,id: "+id+"\t"+"。(^v^)。";
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCirCuitBreakerFallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),//开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")//失败率达到多少后跳闸
    })
    public String paymentCirCuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCirCuitBreakerFallback(@PathVariable("id") Integer id){
        return "id 不能为负数，id:"+id;
    }

}
