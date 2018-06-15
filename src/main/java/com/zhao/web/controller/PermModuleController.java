package com.zhao.web.controller;

import com.zhao.pojo.PermissionModule;
import com.zhao.service.PermModuleService;
import com.zhao.util.BeanValidator;
import com.zhao.util.IpUtil;
import com.zhao.util.JsonObj;
import com.zhao.util.RequestHolder;
import com.zhao.vo.PermModuleVo;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 10:16
 * @描述
 */
@Controller
@RequestMapping("/permModule")
public class PermModuleController {
    private PermModuleService service;

    @Autowired
    public void setService(PermModuleService service) {
        this.service = service;
    }

    @RequestMapping(value = "/save.json",method = RequestMethod.POST)
    @ResponseBody
    public JsonObj save(@Valid PermissionModule permissionModule,BindingResult result){
        Map errorMap = BeanValidator.valid(result);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }
        permissionModule.setLevel(service.getPermModuleLevel(permissionModule.getParentId()));
        permissionModule.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        permissionModule.setOperateTime(new Date());
        permissionModule.setOperator(RequestHolder.getLoginUser().getUsername());
       //保存
        service.save(permissionModule);
        return  JsonObj.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonObj update(@Valid PermissionModule permissionModule,BindingResult result){
        Map errorMap = BeanValidator.valid(result);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }
        permissionModule.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        permissionModule.setOperateTime(new Date());
        permissionModule.setOperator(RequestHolder.getLoginUser().getUsername());
        //更新
        service.update(permissionModule);
        return JsonObj.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonObj tree() {
        List<PermModuleVo> permModuleList = service.getPermModuleList();
        return JsonObj.success().setData(permModuleList);
    }

    @RequestMapping("/listPage.page")
    public ModelAndView page(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("acl");
        return  mv;
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonObj delete(@RequestParam("permModuleId") int permModuleId){
        service.deletePermModuleById(permModuleId);
        return  JsonObj.success();
    }
}