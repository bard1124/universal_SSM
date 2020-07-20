package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 教育经历表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysUserEduExpPo {
    /* ID */
    private Integer id;
    /* 用户id */
    private Integer userid;
    /* 学校名称 */
    private String schoolName;
    /* 专业 */
    private String speciality;
    /* 教育开始年 */
    private Integer startYear;
    /* 教育开始月 */
    private Integer startMonth;
    /* 教育结束年 */
    private Integer endYear;
    /* 教育结束月 */
    private Integer endMonth;
    /* 是否在教育中 */
    private Integer isStuding;
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
