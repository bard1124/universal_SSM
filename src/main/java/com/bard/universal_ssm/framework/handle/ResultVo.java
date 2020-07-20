package com.bard.universal_ssm.framework.handle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultVo {
	private String message = "success";
	private Object data;
	public ResultVo() {}
	public ResultVo(Object data) {
		this.data = data;
	}
}
