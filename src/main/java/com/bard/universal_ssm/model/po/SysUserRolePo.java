package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysUserRolePo {
    /* 编号 */
    private Integer id;
    /* 角色id */
    private Integer roleid;
    /* 用户id */
    private Integer userid;
}
