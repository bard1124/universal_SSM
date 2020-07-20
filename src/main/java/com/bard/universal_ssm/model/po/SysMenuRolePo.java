package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单角色表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysMenuRolePo {
    /* 编号 */
    private Integer id;
    /* 角色id */
    private Integer roleid;
    /* 菜单id */
    private Integer menuid;
}
