package org.dsp.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.dsp.springcloud.alibaba.service.StorageService;
import org.dsp.springcloud.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;
    @PostMapping("/storage/decrease")
    public Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        Integer result = storageService.decrease(productId, count);
        if (result>0){
            return new Result(200,"库存扣减成功!");
        }else{
            return new Result(500,"库存扣减失败!");
        }
    }
}
