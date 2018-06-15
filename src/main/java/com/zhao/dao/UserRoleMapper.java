package com.zhao.dao;

import com.zhao.pojo.UserRole;
import com.zhao.vo.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int deleteUserByRole(Integer roleId);

    void insertUsersByRole(@Param("saveUserRoles") List<UserRole> saveUserRoles);

    List<Integer> selectUserIdsByRole(Integer roleId);

    List<UserRoleVo> selectAllUsersByRole(Integer roleId);
}