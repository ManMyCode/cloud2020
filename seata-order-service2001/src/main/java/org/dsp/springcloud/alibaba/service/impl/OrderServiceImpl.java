package org.dsp.springcloud.alibaba.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.alibaba.dao.OrderMapper;
import org.dsp.springcloud.alibaba.entity.Order;
import org.dsp.springcloud.alibaba.service.AccountService;
import org.dsp.springcloud.alibaba.service.OrderService;
import org.dsp.springcloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;


    @Override
    @GlobalTransactional(name="cloud2020_tx_group",rollbackFor = Exception.class)
    public Integer create(Order order) {
        log.info("~~~~~~~~~~~创建订单~~~~~~~~~~~~~~~~~~");
        int result = orderMapper.create(order);
        log.info("~~~~~~~~~~~~~~>订单微服务调用库存微服务，扣减库存Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("~~~~~~~~~~~~~~>订单微服务调用库存微服务，扣减库存End");

        log.info("~~~~~~~~~~~~~~>订单微服务调用账户微服务，账户余额扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("~~~~~~~~~~~~~~>订单微服务调用账户微服务，账户余额扣减End");

        log.info("~~~~~~~~~~~~~~>修改订单状态开始~~~~~~~~~~~~~~~");
        orderMapper.update(order.getUserId(),0);
        log.info("~~~~~~~~~~~~~~>修改订单状态结束~~~~~~~~~~~~~~~");

        log.info("~~~~~~~~~~~~~~>订单完成~~~~~~~~~~~~~~~");
        return result;
    }
}
