package com.zhao.dao;

import com.zhao.pojo.RbacLog;

public interface RbacLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RbacLog record);

    int insertSelective(RbacLog record);

    RbacLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RbacLog record);

    int updateByPrimaryKey(RbacLog record);
}