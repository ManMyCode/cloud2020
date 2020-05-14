package org.dsp.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dsp.springcloud.alibaba.entity.Order;

@Mapper
public interface OrderMapper {
    /**
     * 创建订单
     * @param order
     */
    Integer create(Order order);

    /**
     * 修改订单状态，从零改为1
     * @param userId
     * @param status
     */
    Integer update(@Param("userId") Long userId,@Param("status") Integer status);
}
