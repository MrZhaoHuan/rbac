package com.zhao.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-16 12:20
 * @描述
 */
@Getter
@Setter
public class LogParam {
    private  Integer startIndex = 0;
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private String beforeSeg;
    private String afterSeg;
    private String operator;
    @Pattern(regexp = "(\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2})*",message = "开始时间格式要求yyyy-MM-dd HH:mm:ss")
    private String fromTime;
    @Pattern(regexp = "(\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2})*",message = "结束时间格式要求yyyy-MM-dd HH:mm:ss")
    private String toTime;
    private Integer type;
}