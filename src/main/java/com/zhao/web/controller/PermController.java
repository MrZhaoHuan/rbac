package com.zhao.web.controller;

import com.zhao.pojo.Permission;
import com.zhao.service.PermService;
import com.zhao.util.*;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 15:16
 * @描述
 */
@Controller
@RequestMapping("/perm")
public class PermController {
    private PermService service;

    @Autowired
    public void setService(PermService service) {
        this.service = service;
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public PageObj pageList(@RequestParam("permModuleId") int permModuleId,PageObj pageObj){
        return  service.getPermPageList(permModuleId, pageObj);
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonObj save(@Valid Permission permission,BindingResult bindingResult){
        Map errorMap = BeanValidator.valid(bindingResult);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }
        permission.setOperator(RequestHolder.getLoginUser().getUsername());
        permission.setOperateTime(new Date());
        permission.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        //设置code
        permission.setCode(UUID.randomUUID().toString());
        service.save(permission);
        return  JsonObj.success();
    }


    @RequestMapping("/update.json")
    @ResponseBody
    public JsonObj update(@Valid Permission permission,BindingResult bindingResult){
        Map errorMap = BeanValidator.valid(bindingResult);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }
        permission.setOperator(RequestHolder.getLoginUser().getUsername());
        permission.setOperateTime(new Date());
        permission.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        service.update(permission);
        return  JsonObj.success();
    }
}