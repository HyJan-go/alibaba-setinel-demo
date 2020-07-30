package com.example.sentinelservice.common;

import lombok.Data;

/**
 * @description: 响应类
 * @author: HyJan
 * @create: 2020-07-30 14:41
 **/
@Data
public class Result<T> {

    private T data;
    private String msg;
    private Integer code;

    public Result(T data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public Result(String msg, Integer code) {
        this(null, msg, code);
    }

    public Result(Integer code) {
        this(null, "", code);
    }

    public static <T> Result success(T data) {
        return new Result(data, "success", 200);
    }

    public static <T> Result error(T data) {
        return new Result(data, "error", 500);
    }

    public static Result build(String msg, Integer code) {
        return new Result(msg, code);
    }

    public static Result build(Integer code) {
        return new Result(code);
    }
}
