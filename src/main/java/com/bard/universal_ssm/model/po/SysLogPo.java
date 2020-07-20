package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 日志表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysLogPo {
    /* id */
    private Integer id;
    /* 日志产生时间 */
    private Date createTime;
    /* 异常信息 */
    private String exceptionDetail;
    /* 结果 */
    private String result;
    /* 日志操作类型 1登录日志2操作日志 */
    private Integer logType;
    /* 请求IP */
    private String requestIp;
    /* 用户名 */
    private String username;
    /* 登录名 */
    private String loginName;
    /* 描述 */
    private String description;
    /* 机构ID */
    private Long orgId;
    /* 所属组件 */
    private String aboutComponent;
}
