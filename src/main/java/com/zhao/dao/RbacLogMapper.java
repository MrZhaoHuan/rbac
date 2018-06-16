package com.zhao.dao;

import com.zhao.param.LogParam;
import com.zhao.pojo.RbacLog;
import com.zhao.vo.LogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RbacLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RbacLog record);

    int insertSelective(RbacLog record);

    RbacLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RbacLog record);

    int updateByPrimaryKey(RbacLog record);

    List<LogVo> selectPageList(LogParam logParam);

    void insertBatchSelective(@Param("rbacLogs")List<RbacLog> rbacLogs);
}