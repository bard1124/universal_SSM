package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysUserPo {
    /* 用户ID */
    private Integer id;
    /* 登入帐号 */
    private String loginNo;
    /* 用户名 */
    private String userName;
    /* 密码 */
    private String password;
    /* 手势密码 */
    private String gesture;
    /* 性别 */
    private Integer sex;
    /* 微信openId */
    private String openId;
    /* 邮箱 */
    private String email;
    /* 手机号码 */
    private String mobilePhone;
    /* 职位 */
    private String position;
    /* 是否管理员 */
    private Integer isAdmin;
    /* 是否内部测试员 */
    private Integer isTest;
    /* 允许多客户端登入 */
    private Integer allowMultiLogin;
    /* 家乡 */
    private String home;
    /* 出生日期 */
    private Date birth;
    /* 入职日期 */
    private Date employment;
    /* 自我介绍 */
    private String introduce;
    /* 社交帐号 */
    private String sns;
    /* 业务专长 */
    private String specialSkill;
    /* 兴趣爱好 */
    private String hobby;
    /* 部门id */
    private Integer dprtmentId;
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
