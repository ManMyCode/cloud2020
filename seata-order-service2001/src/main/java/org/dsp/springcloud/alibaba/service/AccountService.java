package org.dsp.springcloud.alibaba.service;

import org.dsp.springcloud.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value="seata-account-service")
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/account/decrease")
    Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
