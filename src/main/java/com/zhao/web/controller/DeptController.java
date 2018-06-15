package com.zhao.web.controller;

import com.zhao.pojo.Dept;
import com.zhao.service.DeptService;
import com.zhao.util.BeanValidator;
import com.zhao.util.IpUtil;
import com.zhao.util.JsonObj;
import com.zhao.util.RequestHolder;
import com.zhao.vo.DeptVo;
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
 * @创建时间 2018-06-12 23:24
 * @描述 部门操作:查询tree列表,添加,修改
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    private DeptService service;

    @Autowired
    public void setService(DeptService service) {
        this.service = service;
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonObj save(@Valid Dept dept, BindingResult bindingResult) {
        Map errorMap = BeanValidator.valid(bindingResult);
        if (!errorMap.isEmpty()) {
            throw new ParamException(errorMap.toString());
        }

        dept.setLevel(service.getDeptLevel(dept.getParentId()));
        dept.setOperator(RequestHolder.getLoginUser().getUsername());
        dept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        dept.setOperateTime(new Date());
        //保存部门
        service.saveDept(dept);
        return JsonObj.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonObj update(@Valid Dept dept, BindingResult bindingResult) {
        Map errorMap = BeanValidator.valid(bindingResult);
        if (!errorMap.isEmpty()) {
            throw new ParamException(errorMap.toString());
        }
        dept.setOperator(RequestHolder.getLoginUser().getUsername());
        dept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        dept.setOperateTime(new Date());
        //更新部门
        service.updateDept(dept);
        return JsonObj.success();
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonObj delete(@RequestParam("deptId")Integer deptId) {
        service.deleteDeptById(deptId);
        return JsonObj.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonObj tree() {
        List<DeptVo> deptList = service.getDeptList();
        return JsonObj.success().setData(deptList);
    }

    @RequestMapping("/listPage.page")
    public ModelAndView listPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dept");
        return mv;
    }
}