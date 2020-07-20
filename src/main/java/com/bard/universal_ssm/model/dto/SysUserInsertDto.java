package com.bard.universal_ssm.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysUserInsertDto {
    /* 登入帐号 */
    private String loginNo;
    /* 用户名 */
    private String userName;
    /* 性别 */
    private Integer sex;
    /* 邮箱 */
    private String email;
    /* 手机号码 */
    private String mobilePhone;
    /* 职位 */
    private String position;
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
}
