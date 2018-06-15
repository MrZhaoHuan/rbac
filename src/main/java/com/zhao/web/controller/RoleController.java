package com.zhao.web.controller;

import com.zhao.pojo.Role;
import com.zhao.service.RoleService;
import com.zhao.util.BeanValidator;
import com.zhao.util.IpUtil;
import com.zhao.util.JsonObj;
import com.zhao.util.RequestHolder;
import com.zhao.vo.PermModuleVo;
import com.zhao.vo.RoleVo;
import com.zhao.vo.UserRoleVo;
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
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 19:56
 * @描述
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private RoleService service;

    @Autowired
    public void setService(RoleService service) {
        this.service = service;
    }

    @RequestMapping("/role.page")
    public ModelAndView page(){
        ModelAndView mv  = new ModelAndView();
        mv.setViewName("role");
        return  mv;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonObj list(){
        List<RoleVo> roles=  service.getAllRole();
        return  JsonObj.success().setData(roles);
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonObj save(@Valid Role role,BindingResult result){
        //参数校验结果
        Map errorMap = BeanValidator.valid(result);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }
        role.setOperator(RequestHolder.getLoginUser().getUsername());
        role.setOperateTime(new Date());
        role.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));

        service.save(role);
        return  JsonObj.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonObj update(@Valid Role role,BindingResult result){
        //参数校验结果
        Map errorMap = BeanValidator.valid(result);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }
        role.setOperator(RequestHolder.getLoginUser().getUsername());
        role.setOperateTime(new Date());
        role.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        service.update(role);
        return  JsonObj.success();
    }

    @RequestMapping("/roleTree.json")
    @ResponseBody
    public JsonObj rolePerm(@RequestParam("roleId") int roleId){
        List<PermModuleVo> permModuleVos =  service.getRolePermTree(roleId);
        return JsonObj.success().setData(permModuleVos);
    }

    @RequestMapping("/users.json")
    @ResponseBody
    public JsonObj roleUser(@RequestParam("roleId") int roleId){
        Map<String,List<UserRoleVo>> userMap =  service.getUsers(roleId);
        return JsonObj.success().setData(userMap);
    }


    @RequestMapping("/changePerms.json")
    @ResponseBody
    public JsonObj changePerms(@RequestParam("roleId") Integer roleId,@RequestParam(value = "aclIds",defaultValue = "") String aclIds){
        service.savePerm(roleId,aclIds);
        return  JsonObj.success();
    }


    @RequestMapping("/changeUsers.json")
    @ResponseBody
    public JsonObj changeUsers(@RequestParam("roleId") Integer roleId,@RequestParam(value = "userIds",defaultValue = "") String userIds){
        service.saveUsers(roleId, userIds);
        return  JsonObj.success();
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonObj deleteRole(@RequestParam("roleId") Integer roleId){
        service.deleteRole(roleId);
        return  JsonObj.success();
    }
}