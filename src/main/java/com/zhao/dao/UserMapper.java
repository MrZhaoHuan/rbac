package com.zhao.dao;

import com.zhao.pojo.User;
import com.zhao.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPhoneOrEmail(String username);

    int selectCountByTelePhone(@Param("telephone") String telephone, @Param("id")  Integer id);

    int selectCountByEmail(@Param("email")  String email, @Param("id") Integer id);

    List<UserVo> selectPageList(@Param("deptId")String deptId, @Param("startIndex")int startIndex,@Param("pageSize") int pageSize);
}