package org.dsp.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.dsp.springcloud.common.Result;

public class CustomerBlockHandler {
    public static Result handlerException(BlockException exception){
        return new Result(300,"按客户自定义，global handlerException-----------1");
    }
    public static Result handlerException2(BlockException exception){
        return new Result(300,"按客户自定义，global handlerException------------2");
    }
}
