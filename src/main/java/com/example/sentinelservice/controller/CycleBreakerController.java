package com.example.sentinelservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.example.sentinelservice.common.Result;
import com.example.sentinelservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 熔断功能
 * @author: HyJan
 * @create: 2020-07-30 15:54
 **/
@RestController
@Slf4j
@RequestMapping("/breaker")
public class CycleBreakerController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    public String userServiceUrl;

    @RequestMapping("fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handleFallback")
    public Result fallback(@PathVariable Long id) {
        log.info("coming.... >>> baseUrl is {}", userServiceUrl);
        Result result = restTemplate.getForObject( userServiceUrl + "/user/get/{1}", Result.class, id);
        log.info("the result is >>> {}", JSON.toJSONString(result));
        return result;
    }

    public Result handleFallback(Long id){
        return Result.error(new User(-1224L,"haha","fasdgad"));
    }

    @RequestMapping("/fallbackException/{id}")
    @SentinelResource(value = "fallbackException",fallback = "handleFallback2", exceptionsToIgnore = {NullPointerException.class})
    public Result fallbackException(@PathVariable Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/get/{1}", Result.class, id);
    }

    public Result handleFallback2(@PathVariable Long id, Throwable e) {
        log.error("handleFallback2 id:{},throwable class:{}", id, e.getClass());
        User defaultUser = new User(-2L, "defaultUser2", "123456");
        return new Result(defaultUser,"服务降级返回",200);
    }
}
