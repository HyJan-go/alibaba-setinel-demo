package com.example.sentinelservice.controller;

import com.example.sentinelservice.common.Result;
import com.example.sentinelservice.service.UserServiceRemote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: feign 远程调用测试
 * @author: HyJan
 * @create: 2020-07-30 17:07
 **/
@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Resource
    private UserServiceRemote userServiceRemote;

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id) {
        return userServiceRemote.getUserById(id);
    }

    @GetMapping("/get-by-name")
    public Result getByName(String name) {
        return userServiceRemote.getByName(name);
    }

    @GetMapping("/get/list")
    public Result getList() {
        return userServiceRemote.getList();
    }
}
