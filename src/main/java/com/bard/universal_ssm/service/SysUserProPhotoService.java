package com.bard.universal_ssm.service;

import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.po.SysUserProPhotoPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@CacheConfig(cacheNames = "profile_photo")
public interface SysUserProPhotoService {

	@Cacheable(key = "#p0")
	public List<SysUserProPhotoPo> getProfilePhoto(Integer userId);

	@CacheEvict(key = "#p1.getId()")
	public void insertProfilePhoto(MultipartFile img, LoginUserBo loginUserBo) throws BusinessException;

}
