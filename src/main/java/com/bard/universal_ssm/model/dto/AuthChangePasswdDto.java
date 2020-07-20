package com.bard.universal_ssm.model.dto;

import com.bard.universal_ssm.framework.validation.annotation.Column;
import com.bard.universal_ssm.framework.validation.annotation.NotEmpty;
import com.bard.universal_ssm.framework.validation.annotation.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthChangePasswdDto {
	@NotNull @NotEmpty @Column("用户名")
	private String account;
	@NotNull @NotEmpty @Column("密码")
	private String password;
	@NotNull @NotEmpty @Column("更改的密码")
	private String changedPassword;
}
