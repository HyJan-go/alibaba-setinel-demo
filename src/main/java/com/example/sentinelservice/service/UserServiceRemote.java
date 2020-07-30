package com.example.sentinelservice.service;

import com.example.sentinelservice.common.Result;
import com.example.sentinelservice.service.impl.UserServiceRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 远程调用用户服务
 * @author: HyJan
 * @create: 2020-07-30 14:52
 **/
// value 是对应的服务名称，path是服务的访问路径前缀，跟controller一样
@FeignClient(value = "nacos-user-service",path = "/user",fallback = UserServiceRemoteHystrix.class)
public interface UserServiceRemote {

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping(value = "/get-list",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result getList();

    /**
     * 根据id获取用户信息
     * 使用Feign接口调用远程服务的方法时，定义各参数绑定，@PathVariable、@RequestParam、@RequestHeader等可以指定参数属性，在
     * Feign中绑定参数必须通过value属性来明确指明具体的参数名，不然会抛出异常
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result getUserById(@PathVariable(value = "id") Long id);

    /**
     * 通过名字获取用户信息
     * @param name
     * @return
     */
    @GetMapping(value = "get-by-name",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result getByName(String name);
}
