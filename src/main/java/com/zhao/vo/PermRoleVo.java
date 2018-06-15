package com.zhao.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-15 9:19
 * @描述
 */
@Getter
@Setter
public class PermRoleVo {
    private boolean checked; //角色是否有权限
    private boolean hasPerm; //用户是否有权分配这个权限点
    private Integer id;
    private String name;
    private Integer permModuleId;
    private String status;
    private Byte seq;
    private String type;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PermRoleVo)?((PermRoleVo)obj).getId().equals(id):false;
    }
}
