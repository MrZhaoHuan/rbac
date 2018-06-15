package com.zhao.service;

import com.zhao.dao.UserRoleMapper;
import com.zhao.pojo.UserRole;
import com.zhao.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-15 15:27
 * @描述
 */
@Service
public class UserRoleService {
    private UserRoleMapper mapper;

    @Autowired
    public void setMapper(UserRoleMapper mapper) {
        this.mapper = mapper;
    }

    public boolean deleteUserByRole(Integer roleId){
        int count = mapper.deleteUserByRole(roleId);
        return  true;
    }

    public List<Integer> selectUserIdsByRole(Integer roleId) {
       return mapper.selectUserIdsByRole(roleId);
    }

    public void insertUsersByRole(List<UserRole> saveUserRoles) {
        mapper.insertUsersByRole(saveUserRoles);
    }

    public List<UserRoleVo> selectAllUsersByRole(Integer roleId){
       return  mapper.selectAllUsersByRole(roleId);
    }
}
