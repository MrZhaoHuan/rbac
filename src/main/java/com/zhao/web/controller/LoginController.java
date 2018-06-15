package com.zhao.web.controller;

import com.zhao.pojo.User;
import com.zhao.service.UserService;
import com.zhao.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-13 17:20
 * @描述
 */
@Controller
public class LoginController{

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login.page")
    public void login(HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
           String ret = request.getParameter("ret");
           String username = request.getParameter("username");
           String password = request.getParameter("password");

           String errorMsg = "";
           User user = new User();
           if( StringUtils.isEmpty(username)){
               errorMsg = "用户名不能为空";
           }else if( StringUtils.isEmpty(password)){
               errorMsg = "密码不能为空";
           }else{
               //数据库查询用户
               user =  service.selectByPhoneOrEmail(username);

               if(user==null){
                   errorMsg = "用户名不存在";
               }else if(!user.getPassword().equals(MD5Utils.encrypt(password))){
                   errorMsg = "用户名或密码错误";
               }else if(user.getStatus().equals("1") || user.getStatus().equals("2")){
                   errorMsg = "该账号已被冻结";
               }else{
                   //登录成功
                   request.getSession().setAttribute("user",user);
                   if(!StringUtils.isEmpty(ret)){
                       response.sendRedirect(ret);
                   }else{
                       response.sendRedirect(request.getContextPath()+"/admin.page");
                   }
                   return;
               }

           }
           request.setAttribute("error",errorMsg);
           request.setAttribute("username",username);
           request.getRequestDispatcher("signin.jsp").forward(request,response);
    }

    @RequestMapping("/admin.page")
    public ModelAndView defaultIndex(){
        ModelAndView mv=  new ModelAndView();
        mv.setViewName("admin");
        return  mv;
    }
}