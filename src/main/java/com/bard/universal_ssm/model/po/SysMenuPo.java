package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysMenuPo {
    /* 编号 */
    private Integer id;
    /* 菜单名称 */
    private String name;
    /* 菜单url */
    private String url;
    /* 显示顺序 */
    private Integer showorder;
    /* 图标 */
    private String iconclass;
    /* 类型 */
    private Integer type;
    /* 父节点 */
    private Integer parentid;
    /* 状态 */
    private Integer status;
}
