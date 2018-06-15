package com.zhao.util;

import com.zhao.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 0:18
 * @描述
 */
public class RequestHolder {
    private static final ThreadLocal<User> USER = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<>();

    public static void setUser(User user) {
        USER.set(user);
    }

    public static void setRequest(HttpServletRequest request) {
        REQUEST.set(request);
    }

    /*
      *@描述  获取登录用户
      *@创建时间 2018/6/14 0:22
      *@返回值 User
      **/
    public static User getLoginUser() {
        return USER.get();
    }

    public static void remove() {
        USER.remove();
        REQUEST.remove();
    }

    /*
     *@描述  获取当前request
     *@创建时间 2018/6/14 0:25
     *@返回值 HttpServletRequest
     **/
    public static HttpServletRequest getCurrentRequest() {
        return REQUEST.get();
    }

}