package com.bard.universal_ssm.service.impl;

import com.bard.universal_ssm.dao.SysTokenDao;
import com.bard.universal_ssm.model.po.SysTokenPo;
import com.bard.universal_ssm.service.SysTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysTokenService")
public class SysTokenServiceImpl implements SysTokenService {
	
	@Autowired
	private SysTokenService sysTokenService;
	
	@Autowired
	private SysTokenDao sysTokenDao;

	@Override
	public Integer insert(SysTokenPo sysTokenPo) {
		return sysTokenDao.insert(sysTokenPo);
	}

	@Override
	public List<SysTokenPo> update(SysTokenPo sysTokenPo) {
		List<SysTokenPo> sysTokenPoList = sysTokenService.getTokenByUserid(sysTokenPo.getUserid());
		for(int i = 0; i < sysTokenPoList.size(); i++) {
			if(sysTokenPoList.get(i).getId() == sysTokenPo.getId()) {
				sysTokenPoList.set(i, sysTokenPo);
				break;
			}
		}
		sysTokenDao.update(sysTokenPo);
		return sysTokenPoList;
	}

	@Override
	public List<SysTokenPo> getTokenByUserid(Integer userid) {
		return sysTokenDao.selectTokenByUserid(userid);
	}

	@Override
	public SysTokenPo getToken(String token) {
		return sysTokenDao.selectToken(token);
	}

}
