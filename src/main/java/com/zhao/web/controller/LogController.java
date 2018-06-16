package com.zhao.web.controller;

import com.zhao.service.LogService;
import com.zhao.util.BeanValidator;
import com.zhao.util.JsonObj;
import com.zhao.util.PageObj;
import com.zhao.param.LogParam;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-16 12:13
 * @描述  权限更新记录
 */
@Controller
@RequestMapping("/log")
public class LogController {
    private LogService service;

    @Autowired
    public void setService(LogService service) {
        this.service = service;
    }

    @RequestMapping("/log.page")
    public ModelAndView logPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("log");
        return mv;
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public PageObj list(@Valid LogParam logParam,BindingResult result){
        Map errorMap = BeanValidator.valid(result);
        if(!errorMap.isEmpty()){
            throw new ParamException(errorMap.toString());
        }

        return  service.getLogPageList(logParam);
    }

    @RequestMapping("/recover.json")
    @ResponseBody
    public JsonObj recover(@RequestParam("id")int logId){
        service.recoverData(logId);
        return  JsonObj.success();
    }
}