package com.zhao.vo;

import com.zhao.pojo.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-13 23:50
 * @描述  增加了1个分页信息:总条数
 */
@Getter
@Setter
public class UserVo extends User{
    private int total;
}
