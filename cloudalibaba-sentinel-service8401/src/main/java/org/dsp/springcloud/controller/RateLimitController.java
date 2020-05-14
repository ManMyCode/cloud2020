package org.dsp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.dsp.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public Result byResource(){
        return new Result(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    public Result handleException(BlockException exception){
        return new Result(500,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public Result byUrl(){
        return new Result(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public Result customerBlockHandler(){
        return new Result(200,"按客户自定义",new Payment(2020L,"serial003"));
    }
}
