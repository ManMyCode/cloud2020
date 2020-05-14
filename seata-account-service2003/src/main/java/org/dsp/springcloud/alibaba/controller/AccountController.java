package org.dsp.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.alibaba.service.AccountService;
import org.dsp.springcloud.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;
    @PostMapping("/account/decrease")
    public Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        Integer count = accountService.decrease(userId, money);
        if (count>0){
            return new Result(200,"账户余额扣减成功!");
        }else{
            return new Result(500,"账户余额扣减失败!");
        }
    }
}
