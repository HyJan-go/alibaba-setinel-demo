package com.example.sentinelservice.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.sentinelservice.common.Result;

/**
 * @description: 限流处理
 * @author: HyJan
 * @create: 2020-07-30 14:10
 **/
public class CustomBlockHandler {

    public Result handleException(BlockException exception){
        return Result.build("自定义限流信息",200);
    }
}
