package com.bard.universal_ssm.service;

import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.dto.RoleDto;
import com.bard.universal_ssm.model.dto.SysUserInsertDto;
import com.bard.universal_ssm.model.dto.SysUserSelectDto;
import com.bard.universal_ssm.model.dto.SysUserUpdateDto;
import com.bard.universal_ssm.model.po.SysUserPo;
import com.bard.universal_ssm.model.po.SysUserPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "user")
public interface SysUserService {
    public List<SysUserPo> getAllUser(SysUserSelectDto sysUserSelectDto);
    //public PageInfo<SysUserPo> getAllUser(SysUserSelectDto sysUserSelectDto);

    public void insertUser(SysUserInsertDto sysUserInsertDto, LoginUserBo loginUserBo) throws BusinessException;

    @Cacheable(key = "#p0")
    public SysUserPo getUser(Integer id);

    @CacheEvict(key = "#p0")
    public void deleteUser(Integer id);

    @CachePut(key = "#p0")
    public SysUserPo updateUser(Integer id, SysUserUpdateDto sysUserUpdateDto, LoginUserBo loginUserBo) throws BusinessException;

    //public LoginUserBo getLoginUser(Integer id, Integer term) throws BusinessException;
    public LoginUserBo getLoginUser(Integer id) throws BusinessException;

    List<RoleDto> selectUserRole(Integer id);

    String updateUserRole(Integer id, List<Integer> roleIds);
}
