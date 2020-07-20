package com.bard.universal_ssm.service.impl;

import com.bard.universal_ssm.dao.SysUserDao;
import com.bard.universal_ssm.framework.constants.Constant;
import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.utils.AuthUtils;
import com.bard.universal_ssm.framework.utils.ObjectUtils;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.dto.AuthChangePasswdDto;
import com.bard.universal_ssm.model.dto.AuthDto;
import com.bard.universal_ssm.model.po.SysTokenPo;
import com.bard.universal_ssm.model.po.SysUserPo;
import com.bard.universal_ssm.service.AuthService;
import com.bard.universal_ssm.service.SysTokenService;
import com.bard.universal_ssm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysTokenService sysTokenService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public LoginUserBo login(AuthDto authDto) throws BusinessException {
		SysUserPo sysUserPo = sysUserDao.selectUserByAccount(authDto.getAccount());
		if(sysUserPo == null)
			throw new BusinessException(Message.USER_NOT_EXIST);
		if(!sysUserPo.getPassword().equals(AuthUtils.encodePassword(authDto.getPassword())))
			throw new BusinessException(Message.USER_PASSWORD_INCORRECT);
		List<SysTokenPo> sysTokenPoList = sysTokenService.getTokenByUserid(sysUserPo.getId());
		//if(sysUserPo.getAllowMultiLogin() != 1)
		//	authDto.setTerm(0);
		SysTokenPo sysTokenPo = null;
		for(SysTokenPo po: sysTokenPoList) {
			//if(po.getTerm() == authDto.getTerm())
				sysTokenPo = po;
		}
		if(sysTokenPo == null) {
			sysTokenPo = new SysTokenPo();
			sysTokenPo.setExpiredDate(Constant.TOKEN_EXPIRE);
			sysTokenPo.setToken(AuthUtils.genToken());
			sysTokenPo.setLoginTime(new Date());
			//sysTokenPo.setTerm(authDto.getTerm());
			sysTokenPo.setUserid(sysUserPo.getId());
			sysTokenPo.setUpdateTime(new Date());
			sysTokenService.insert(sysTokenPo);
		} else {
			/*if(sysUserPo.getIsTest() != 1) {
				sysTokenPo.setToken(AuthUtils.genToken());
			}*/
			sysTokenPo.setUpdateTime(new Date());
			sysTokenPo.setLoginTime(new Date());
			sysTokenService.update(sysTokenPo);
		}
		//return sysUserService.getLoginUser(sysUserPo.getId(), authDto.getTerm());
		return sysUserService.getLoginUser(sysUserPo.getId());
	}

	@Override
	public Boolean checkPassword(AuthDto authDto, LoginUserBo loginUserBo) {
		return loginUserBo.getPassword().equals(AuthUtils.encodePassword(authDto.getPassword()));
	}

	@Override
	public SysUserPo updatePassword(AuthChangePasswdDto authChangePasswdDto, LoginUserBo loginUserBo) {
		SysUserPo sysUserPo = sysUserService.getUser(loginUserBo.getId());
		if(checkPassword(ObjectUtils.objectMap(authChangePasswdDto, AuthDto.class), loginUserBo)) {
			sysUserPo.setPassword(AuthUtils.encodePassword(authChangePasswdDto.getChangedPassword()));
			sysUserPo.setUpdateDate(new Date());
			sysUserPo.setUpdateUser(loginUserBo.getUserName());
			sysUserDao.update(sysUserPo);
		}
		return sysUserPo;
	}

}
