package com.zhao.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-12 23:30
 * @描述  部门tree展示
 */
@Getter
@Setter
@ToString
public class DeptVo{

    private Integer id;

    private String name;

    private Integer parentId;

    private String remark;

    private Byte seq;

    private List<DeptVo> deptVoList = new ArrayList<>();
}