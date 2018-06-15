package com.zhao.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-14 10:49
 * @描述  权限模块tree展示
 */
@Getter
@Setter
public class PermModuleVo {
    private Integer id;

    private String name;

    private Integer parentId;

    private String remark;

    private Byte seq;

    private List<PermModuleVo> permModuleVoList = new ArrayList<>();


    private List<PermRoleVo> permRoleVoList = new ArrayList<>();

}