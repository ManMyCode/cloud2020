package org.dsp.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {
    /**
     * 扣减账户余额
     * @param userId
     * @param money
     * @return
     */
    Integer decrease(@Param("userId") Long userId, @Param("money")BigDecimal money);
}
