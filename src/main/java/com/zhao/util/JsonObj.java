package com.zhao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-12 22:40
 * @描述  返回到客户端的数据格式
 */
public class JsonObj {
    private String code;
    private String message;
    private Object data;
    private static  final  String successCode = "0";
    private static  final  String errorCode = "1";

    private JsonObj setCode(String code) {
        this.code = code;
        return this;
    }

    private JsonObj setMessage(String message) {
        this.message = message;
        return this;
    }

    public JsonObj setData(Object data) {
        this.data = data;
        return this;
    }

    public static JsonObj success() {
        return new JsonObj().setCode(successCode);
    }

    public static JsonObj error(String message) {
        return new JsonObj().setCode(errorCode).setMessage(message);
    }

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Map toMap(){
        Map map = new HashMap();
        map.put("code",code);
        map.put("message",message);
        map.put("data",data);
        return  map;
    }
}