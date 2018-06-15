package com.zhao.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-13 21:00
 * @描述  分页数据格式
 */
@Getter
@Setter
public class PageObj{
    private String code;
    private String message;
    private Map<String,Object> data = new HashMap<>();
    private int pageSize;
    private int pageNo;
}