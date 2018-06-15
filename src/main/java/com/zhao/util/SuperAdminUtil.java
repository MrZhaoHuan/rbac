package com.zhao.util;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-15 22:14
 * @描述  是否是超级管理员
 */
public class SuperAdminUtil {
    public static boolean isSuperAdmin(){
        String username=  RequestHolder.getLoginUser().getUsername();
        return  "admin".equals(username)?true:false;
    }
}
