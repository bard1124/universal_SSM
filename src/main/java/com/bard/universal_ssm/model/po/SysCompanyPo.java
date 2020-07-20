package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 公司表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysCompanyPo {
    /* 公司ID */
    private Integer id;
    /* 公司名称 */
    private String companyName;
    /* 公司主页 */
    private String homePage;
    /* 公司简介 */
    private String companyProfile;
    /* 电话号 */
    private String phoneNumber;
    /* 传真 */
    private String fax;
    /* 邮箱 */
    private String email;
    /* 法人代表 */
    private String legalRepre;
    /* 公司地址 */
    private String address;
    /* 排序 */
    private Integer showOrder;
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
