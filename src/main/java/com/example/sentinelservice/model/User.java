package com.example.sentinelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 测试实体类
 * @author: HyJan
 * @create: 2020-07-30 14:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;

}
