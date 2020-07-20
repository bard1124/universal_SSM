package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * token表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysTokenPo {
    /* 编号 */
    private Integer id;
    /* token令牌 */
    private String token;
    /* 用户编号 */
    private Integer userid;
    /* 登录时间 */
    private Date loginTime;
    /* 修改时间 */
    private Date updateTime;
    /* 有效时间 */
    private Integer expiredDate;
}
