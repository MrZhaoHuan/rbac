package com.zhao.web.controller;

import com.zhao.pojo.User;
import com.zhao.service.UserService;
import com.zhao.util.*;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-13 18:38
 * @描述
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/save.json")
    @ResponseBody
    public JsonObj save(@Valid User user, BindingResult bindingResult) {
        Map errorResult = BeanValidator.valid(bindingResult);
        if (!errorResult.isEmpty()) {
            throw new ParamException(errorResult.toString());
        }
        user.setOperator(RequestHolder.getLoginUser().getUsername());
        user.setOperateTime(new Date());
        user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        //密码md5加密，保存用户
        user.setPassword(MD5Utils.encrypt(RandomPasswordUtil.getPassword()));
        service.saveUser(user);
        return JsonObj.success();
    }

    @RequestMapping(value = "/update.json")
    @ResponseBody
    public JsonObj update(@Valid User user, BindingResult bindingResult) {
        Map errorResult = BeanValidator.valid(bindingResult);
        if (!errorResult.isEmpty()) {
            throw new ParamException(errorResult.toString());
        }
        user.setOperator(RequestHolder.getLoginUser().getUsername());
        user.setOperateTime(new Date());
        user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        service.updateUser(user);
        return JsonObj.success();
    }


    @RequestMapping("/page.json")
    @ResponseBody
    public PageObj list(@RequestParam("deptId") String deptId,PageObj pageObj){
        return  service.getUserPageObj(deptId, pageObj);
    }

    @RequestMapping("/noAuth.page")
    public ModelAndView noAuth(){
        ModelAndView mv=  new ModelAndView();
        mv.setViewName("noAuth");
        return  mv;
    }
}