package com.bard.universal_ssm.model.bo;

import com.bard.universal_ssm.model.po.SysTokenPo;
import com.bard.universal_ssm.model.po.SysUserProPhotoPo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LoginUserBo {
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
    /* Token */
    private SysTokenPo token;
    /* 个人头像 */
    private List<SysUserProPhotoPo> proPhotoList;
    /* 部门公司 */
    private SysDprtmentBo dprtment;
}
