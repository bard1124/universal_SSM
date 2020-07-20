package com.bard.universal_ssm.service;


import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.dto.AuthChangePasswdDto;
import com.bard.universal_ssm.model.dto.AuthDto;
import com.bard.universal_ssm.model.po.SysUserPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
public interface AuthService {

	public LoginUserBo login(AuthDto authDto) throws BusinessException;

	public Boolean checkPassword(AuthDto authDto, LoginUserBo loginUserBo);

	public SysUserPo updatePassword(AuthChangePasswdDto authChangePasswdDto, LoginUserBo loginUserBo);

}
