package org.dsp.springcloud.alibaba.controller;

import org.dsp.springcloud.alibaba.entity.Order;
import org.dsp.springcloud.alibaba.service.OrderService;
import org.dsp.springcloud.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public Result create(Order order){
        int count = orderService.create(order);
        if (count>0){
            return new Result(200,"订单创建成功");
        }else{
            return new Result(500,"订单创建失败");
        }

    }
}
