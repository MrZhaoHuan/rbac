package com.zhao.dao;

import com.zhao.pojo.PermissionModule;
import com.zhao.vo.PermModuleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionModule record);

    int insertSelective(PermissionModule record);

    PermissionModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionModule record);

    int updateByPrimaryKey(PermissionModule record);

    String selectPermModuleLevel(Integer parentId);

    int selectPermModuleCountBySameLevel(@Param("parentId") Integer parentId, @Param("name") String name,@Param("id") Integer id);

    List<PermissionModule> selectAllChildPermModule(String level);

    void batchUpdate(List<PermissionModule> childPermModules);

    List<PermModuleVo> selectAllPermModuleVo();

    int selectChildCount(int permModuleId);

    int selectPermCountByPermModule(int permModuleId);
}