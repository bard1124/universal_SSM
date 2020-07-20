package com.bard.universal_ssm.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysOptionVo {
	//id
	private Integer id;
	//值
	private String value;
	//文本
	private String text;
	//子节点
	private List<SysOptionVo> children;
}
