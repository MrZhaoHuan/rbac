package com.zhao.dao;

import com.zhao.pojo.Dept;
import com.zhao.vo.DeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    int selectDeptCountBySameLevel(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

    List<DeptVo> selectAllDept();

    String selectDeptLevel(int id);

    List<Dept> selectAllChildDept(String level);

    void batchUpdate(List<Dept> depts);

    int selectUserCountByDept(Integer deptId);

    int selectChildDeptCount(Integer deptId);
}