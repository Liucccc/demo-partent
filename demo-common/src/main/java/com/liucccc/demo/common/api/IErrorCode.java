package com.liucccc.demo.common.api;

/**
 * 封装API的错误码
 *
 * @author liuchao
 * @date 2020/2/6 10:56
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}