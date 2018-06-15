package com.zhao.dao;

import com.zhao.pojo.Role;
import com.zhao.pojo.RolePermission;
import com.zhao.vo.PermRoleVo;
import com.zhao.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<RoleVo> selectAll();

    int selectCountByIdAndName(@Param("roleId")Integer roleId,@Param("roleName") String roleName);

    List<PermRoleVo> selectAllPerm();

    List<PermRoleVo> selectPermByUser(Integer userId);

    List<PermRoleVo> selectPermByRole(int roleId);

    void deletePermByRole(Integer roleId);

    void insertPermByRole(@Param("rolePermissions")List<RolePermission> rolePermissions);

    int selectUserCountByRole(Integer roleId);

}