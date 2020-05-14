package org.dsp.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface AccountService {
    /**
     * 扣减账户余额
     * @param userId
     * @param money
     * @return
     */
    Integer decrease(Long userId,BigDecimal money);
}
