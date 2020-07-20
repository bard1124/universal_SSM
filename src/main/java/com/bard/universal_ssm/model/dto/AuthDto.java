package com.bard.universal_ssm.model.dto;

import com.bard.universal_ssm.framework.validation.annotation.Column;
import com.bard.universal_ssm.framework.validation.annotation.NotEmpty;
import com.bard.universal_ssm.framework.validation.annotation.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
	@NotNull @NotEmpty @Column("用户名")
	private String account;
	@NotNull @NotEmpty @Column("密码")
	private String password;
	@NotNull
	private Integer term = 0;
}
