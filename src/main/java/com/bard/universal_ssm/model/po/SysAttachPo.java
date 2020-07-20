package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 附件表数据库持久对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysAttachPo {
    /* ID */
    private Integer id;
    /* 文件ID */
    private Long fileId;
    /* 文件名 */
    private String fileName;
    /* 附件URL */
    private String attachUrl;
    /* 附件大小 */
    private Long attachSize;
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
