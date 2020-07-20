package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 配置表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysOptionPo {
    /* ID */
    private Integer id;
    /* 值 */
    private String value;
    /* 文本 */
    private String text;
    /* 父节点id */
    private Integer pid;
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
