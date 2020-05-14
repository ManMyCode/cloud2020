package org.dsp.springcloud.alibaba.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.alibaba.dao.AccountMapper;
import org.dsp.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Integer decrease(Long userId, BigDecimal money) {
        log.info("------------------>account-service中开始扣减账户余额");
        try{TimeUnit.SECONDS.sleep(20);}catch (InterruptedException e){e.printStackTrace();}
        Integer result = accountMapper.decrease(userId,money);
        log.info("------------------>account-service中完成扣减账户余额");
        return result;
    }
}
