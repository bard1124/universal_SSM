package com.bard.universal_ssm.model.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysOptionBo {
	//id
	private Integer id;
	//值
	private String value;
	//文本
	private String text;
    /* 父节点id */
    private Integer pid;
    /* 状态 */
    private Integer status;
	//子节点
	private List<SysOptionBo> children;
}
