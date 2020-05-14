package org.dsp.springcloud.alibaba.service;
import org.dsp.springcloud.alibaba.entity.Order;

public interface OrderService {
    Integer create(Order order);
}
