package com.zhao.web.exception;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-12 22:43
 * @描述  权限异常
 */
public class PermException extends RuntimeException{
      public PermException(String message){
          super(message);
      }
}