package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户头像表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysUserProPhotoPo {
    /* ID */
    private Integer id;
    /* 用户id */
    private Integer userid;
    /* 头像大小 */
    private Integer size;
    /* 头像url */
    private String imageUrl;
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
