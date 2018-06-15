package com.zhao.service;

import com.zhao.dao.UserMapper;
import com.zhao.pojo.User;
import com.zhao.util.PageObj;
import com.zhao.vo.UserVo;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-13 19:18
 * @描述
 */
@Service
public class UserService {
    private UserMapper mapper;

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    public User selectByPhoneOrEmail(String username) {
        return mapper.selectByPhoneOrEmail(username);
    }

    public boolean saveUser(User user) {
        user.setId(null);
        //校验手机号和邮箱是否存在
        int count = mapper.selectCountByTelePhone(user.getTelephone(), user.getId());
        if (count > 0) {
            throw new ParamException("手机号已经注册过了");
        }
        count = mapper.selectCountByEmail(user.getEmail(), user.getId());
        if (count > 0) {
            throw new ParamException("邮箱已经注册过了");
        }
        return mapper.insertSelective(user) > 0;
    }

    public boolean updateUser(User user) {
        //校验手机号和邮箱是否存在
        int count = mapper.selectCountByTelePhone(user.getTelephone(), user.getId());
        if (count > 0) {
            throw new ParamException("手机号已经注册过了");
        }
        count = mapper.selectCountByEmail(user.getEmail(), user.getId());
        if (count > 0) {
            throw new ParamException("邮箱已经注册过了");
        }
        mapper.updateByPrimaryKeySelective(user);
        return true;
    }

    public PageObj getUserPageObj(String deptId, PageObj pageObj) {
        int pageNo = pageObj.getPageNo()<1?1:pageObj.getPageNo();
        int pageSize = pageObj.getPageSize()<0?0:pageObj.getPageSize();
        int total = 0;  //总条数
        //更正页码和每页数量
        pageObj.setPageNo(pageNo);
        pageObj.setPageSize(pageSize);
        //查询用户列表
        List<UserVo> users = mapper.selectPageList(deptId,(pageNo-1)*pageSize,pageSize);
        if(users==null || users.size()==0){
            users = new ArrayList<>();
        }else{
            total =  users.get(0).getTotal();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",users);
        pageObj.setCode("0");
        pageObj.setData(map);
        return pageObj;
    }
}