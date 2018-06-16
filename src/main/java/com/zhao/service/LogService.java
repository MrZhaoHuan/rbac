package com.zhao.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhao.dao.RbacLogMapper;
import com.zhao.param.LogParam;
import com.zhao.pojo.Dept;
import com.zhao.pojo.RbacLog;
import com.zhao.util.*;
import com.zhao.vo.LogVo;
import com.zhao.web.exception.ParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-16 13:20
 * @描述
 */
@Service
public class LogService {
    private RbacLogMapper mapper;

    @Autowired
    public void setMapper(RbacLogMapper mapper) {
        this.mapper = mapper;
    }

    public PageObj getLogPageList(LogParam logParam) {
        PageObj pageObj = new PageObj();
        pageObj.setPageNo(logParam.getPageNo());
        pageObj.setPageSize(logParam.getPageSize());
        pageObj.setCode("0");

        //设置参数
        logParam.setBeforeSeg("%" + (logParam.getBeforeSeg() == null ? "" : logParam.getBeforeSeg()) + "%");
        logParam.setAfterSeg("%" + (logParam.getAfterSeg()==null?"":logParam.getAfterSeg()) + "%");
        logParam.setOperator("%" + (logParam.getOperator()==null?"":logParam.getOperator()) + "%");
        logParam.setStartIndex((logParam.getPageNo() - 1) * logParam.getPageSize());

        //查询列表
        List<LogVo> logVoList  = mapper.selectPageList(logParam);
        if(logVoList==null || logVoList.size()==0){
            pageObj.getData().put("total",0);
            pageObj.getData().put("data",new ArrayList<>());
        }else{
            pageObj.getData().put("total",logVoList.get(0).getTotal());
            pageObj.getData().put("data",logVoList);
        }
        return pageObj;
    }

    //设置公共信息
    private void setOperatInfo(RbacLog log){
        log.setOperator(RequestHolder.getLoginUser().getUsername());
        log.setOperateTime(new Date());
        log.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
    }
    //保存部门操作日志
    public void saveDeptLog(List<Dept> oldValues, List<Dept>  newValues){
        List<RbacLog> rbacLogs = new ArrayList<>();
        for(Dept newDept:newValues){
            RbacLog log = new RbacLog();
            log.setType(LogType.DEPT);
            log.setTargetid(newDept.getId());
            log.setOldvalue((oldValues == null || oldValues.size() == 0) ? "" : JSONObject.toJSONString(oldValues.get(0)));
            log.setNewvalue(JSONObject.toJSONString(newDept));
            setOperatInfo(log);
            rbacLogs.add(log);
        }
        //批量更新操作日志
        mapper.insertBatchSelective(rbacLogs);
    }

    public void recoverData(int logId) {
        //查询出要还原的数据
        RbacLog rbacLog = mapper.selectByPrimaryKey(logId);
        String type = rbacLog.getType();
        String oldvalue = rbacLog.getOldvalue();
        String newvalue = rbacLog.getNewvalue();
        if(StringUtils.isEmpty(oldvalue)||StringUtils.isEmpty(newvalue)){
             throw new ParamException("只有更新操作才能还原");
        }
        if(!type.equals(LogType.DEPT)){
            throw new ParamException("暂时不支持其他类型还原");
        }
        switch (type){
            case LogType.DEPT:
                //string 转 对象
                Dept dept = JSON.parseObject(rbacLog.getOldvalue(),Dept.class);
                //更新成以前的值
                ApplicationContextUtil.getBean(DeptService.class).updateDept(dept);
                break;
            case LogType.USER:
                break;
            case LogType.PERM_MODULE:
                break;
            case LogType.PERM:

                break;
            case LogType.ROLE:
                break;
            case LogType.ROLE_PERM:
                break;
            case LogType.ROLE_USER:
                break;
        }
    }
}