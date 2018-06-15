package com.zhao.pojo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Permission {
    private Integer id;

    @NotBlank(message = "权限名称不能为空")
    @Length(max =20,min = 2,message = "权限名称长度要求2-20之间")
    private String name;

    private String code;

    @NotNull(message = "所属权限模块不能为空")
    private Integer permmoduleId;

    @NotBlank(message = "权限url不能为空")
    @Length(max =100,message = "权限url长度不超过100")
    private String url;

    private String status;

    @NotNull(message = "顺序不能为空")
    private Byte seq;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;

    @NotNull(message = "菜单类型不能为空")
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public void setPermmoduleId(Integer permmoduleId) {
        this.permmoduleId = permmoduleId;
    }

    public Integer getPermmoduleId() {
        return permmoduleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Byte getSeq() {
        return seq;
    }

    public void setSeq(Byte seq) {
        this.seq = seq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}