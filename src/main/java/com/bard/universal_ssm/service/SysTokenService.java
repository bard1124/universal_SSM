package com.bard.universal_ssm.service;

import com.bard.universal_ssm.model.po.SysTokenPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "token")
public interface SysTokenService {
	@CacheEvict(key = "'uid_' + #p0.getUserid()")
    public Integer insert(SysTokenPo sysTokenPo);
	
	@CachePut(key = "'uid_' + #p0.getUserid()")
    public List<SysTokenPo> update(SysTokenPo sysTokenPo);
	
	@Cacheable(key = "'uid_' + #p0")
    public List<SysTokenPo> getTokenByUserid(Integer userid);

	@Cacheable(key = "'token_' + #p0")
	public SysTokenPo getToken(String token);
}
