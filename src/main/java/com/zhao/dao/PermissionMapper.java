package com.zhao.dao;

import com.zhao.pojo.Permission;
import com.zhao.vo.MenuVo;
import com.zhao.vo.PermVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<PermVo> selectPageList(@Param("permmoduleId") int permModuleId, @Param("startIndex")int startIndex,@Param("pageSize")int pageSize);

    int selectCountBySamePermModule(@Param("permmoduleId")Integer permModuleid, @Param("name")String name,@Param("id") Integer id);

    List<Permission> selectPermBySelect(String url);

    List<Integer> getAllPermByUser(Integer id);

    List<MenuVo> getMenuPermList();
}