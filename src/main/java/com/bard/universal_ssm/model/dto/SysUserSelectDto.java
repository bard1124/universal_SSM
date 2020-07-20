package com.bard.universal_ssm.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户对象
 * @author VBA Tool
 * @version 1.0
 */
@Getter
@Setter
public class SysUserSelectDto extends PageDto {
    /* 搜索关键词 */
    private String loginNo;
}
