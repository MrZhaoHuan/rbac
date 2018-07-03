package com.zhao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-16 20:25
 * @描述
 */
@Controller
@RequestMapping("/ajax")
public class TestAjax {

    @RequestMapping("/data")
    public void response(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("","");
    }
}
