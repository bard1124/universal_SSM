package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 部门表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysDprtmentPo {
    /* 部门ID */
    private Integer id;
    /* 部门名称 */
    private String dprtmentName;
    /* 分机号 */
    private String extNumber;
    /* 公司ID */
    private Integer companyId;
    /* 排序 */
    private Integer orderNo;
    /* 状态 */
    private Integer status;
    /* 创建时间 */
    private Date createDate;
    /* 创建人 */
    private String createUser;
    /* 修改时间 */
    private Date updateDate;
    /* 修改人 */
    private String updateUser;
}
