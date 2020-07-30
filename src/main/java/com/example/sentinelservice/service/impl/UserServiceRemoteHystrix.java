package com.example.sentinelservice.service.impl;

import com.example.sentinelservice.common.Result;
import com.example.sentinelservice.model.User;
import com.example.sentinelservice.service.UserServiceRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 服务降级处理类
 * @author: HyJan
 * @create: 2020-07-30 14:53
 **/
@Component
@Slf4j
public class UserServiceRemoteHystrix implements UserServiceRemote {
    @Override
    public Result getList() {
        log.error("远程调用用户信息列表出错 >>> 服务降级 >>> 返回默认");
        return Result.success(new User(12548L,"大海","1254854fadg"));
    }

    @Override
    public Result getUserById(Long id) {
        log.error("通过id远程调用用户信息出错 >>> 服务降级 >>> 返回默认");
        return Result.success(new User(12548L,"大海","1254854fadg"));
    }

    @Override
    public Result getByName(String name) {
        log.error("通过名称远程调用用户信息错误 >>> 服务降级 >>> 返回默认");
        return Result.success(new User(12548L,"大海","1254854fadg"));
    }
}
