package com.zhao.web.exception;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-12 22:57
 * @描述  参数错误异常
 */
public class ParamException extends RuntimeException{
     public ParamException(String message){
         super(message);
     }
}
