package com.bard.universal_ssm.service.impl;

import com.bard.universal_ssm.dao.SysCompanyDao;
import com.bard.universal_ssm.dao.SysDprtmentDao;
import com.bard.universal_ssm.dao.SysUserDao;
import com.bard.universal_ssm.framework.constants.Constant;
import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.utils.AuthUtils;
import com.bard.universal_ssm.framework.utils.ObjectUtils;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.bo.SysDprtmentBo;
import com.bard.universal_ssm.model.dto.RoleDto;
import com.bard.universal_ssm.model.dto.SysUserInsertDto;
import com.bard.universal_ssm.model.dto.SysUserSelectDto;
import com.bard.universal_ssm.model.dto.SysUserUpdateDto;
import com.bard.universal_ssm.model.po.SysTokenPo;
import com.bard.universal_ssm.model.po.SysUserPo;
import com.bard.universal_ssm.service.SysTokenService;
import com.bard.universal_ssm.service.SysUserProPhotoService;
import com.bard.universal_ssm.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysTokenService sysTokenService;

    @Autowired
    private SysUserProPhotoService sysUserProPhotoService;

    @Autowired
    private SysDprtmentDao sysDprtmentDao;

    @Autowired
    private SysCompanyDao sysCompanyDao;

    public List<SysUserPo> getAllUser(SysUserSelectDto sysUserSelectDto) {
        PageHelper.startPage(sysUserSelectDto.getPageNum(), sysUserSelectDto.getPageSize());
        List<SysUserPo> pos = sysUserDao.selectAllByKey(sysUserSelectDto.getLoginNo());
        //return new PageInfo<SysUserPo>(pos);
        return pos;
    }

    @Override
    public void insertUser(SysUserInsertDto sysUserInsertDto, LoginUserBo loginUserBo) throws BusinessException {
        if (sysUserDao.selectUserByAccount(sysUserInsertDto.getLoginNo()) != null) {
            throw new BusinessException(Message.USER_ID_EXIST);
        }
        if (sysUserDao.selectUserByAccount(sysUserInsertDto.getMobilePhone()) != null) {
            throw new BusinessException(Message.USER_PHONE_EXIST);
        }
        SysUserPo sysUserPo = ObjectUtils.objectMap(sysUserInsertDto, SysUserPo.class);
        sysUserPo.setPassword(AuthUtils.encodePassword(Constant.USER_DEFAULT_PASSWORD));
        sysUserPo.setCreateUser(loginUserBo.getUserName());
        sysUserPo.setCreateDate(new Date());
        sysUserPo.setStatus(1);
        sysUserDao.insert(sysUserPo);
    }

    @Override
    public SysUserPo getUser(Integer id) {
        return sysUserDao.selectOne(id);
    }

    @Override
    public void deleteUser(Integer id) {
        sysUserDao.delete(id);
    }

    @Override
    public SysUserPo updateUser(Integer id, SysUserUpdateDto sysUserUpdateDto, LoginUserBo loginUserBo) throws BusinessException {
		/*if(!loginUserBo.getMobilePhone().equals(sysUserUpdateDto.getMobilePhone())) {
			if(sysUserDao.selectUserByAccount(sysUserUpdateDto.getMobilePhone()) != null) {
				throw new BusinessException(Message.USER_PHONE_EXIST);
			}
		}*/
        SysUserPo sysUserPo = ObjectUtils.objectMap(sysUserUpdateDto, SysUserPo.class);
        sysUserPo.setId(id);
        sysUserPo.setUpdateUser(loginUserBo.getUserName());
        sysUserPo.setUpdateDate(new Date());
        sysUserPo.setStatus(1);
        sysUserDao.update(sysUserPo);
        return sysUserDao.selectOne(id);
    }

    @Override
    public LoginUserBo getLoginUser(Integer id) throws BusinessException {
        SysUserPo sysUserPo = sysUserService.getUser(id);
        LoginUserBo loginUserBo = ObjectUtils.objectMap(sysUserPo, LoginUserBo.class);
        List<SysTokenPo> sysTokenPoList = sysTokenService.getTokenByUserid(id);
        SysTokenPo sysTokenPo = null;
        for (SysTokenPo po : sysTokenPoList) {
            //if (po.getTerm() == term)
            sysTokenPo = po;
        }
        if (sysTokenPo == null)
            throw new BusinessException(Message.USER_NOT_EXIST);
        loginUserBo.setToken(sysTokenPo);
        //loginUserBo.setProPhotoList(sysUserProPhotoService.getProfilePhoto(id));
        /*if (sysUserPo.getDprtmentId() != null) {
            SysDprtmentBo sysDprtmentBo = ObjectUtils.objectMap(sysDprtmentDao.selectOne(sysUserPo.getDprtmentId()), SysDprtmentBo.class);
            if (sysDprtmentBo.getCompanyId() != null)
                sysDprtmentBo.setCompany(sysCompanyDao.selectOne(sysDprtmentBo.getCompanyId()));
            loginUserBo.setDprtment(sysDprtmentBo);
        }*/
        return loginUserBo;
    }

    //@Override
    public LoginUserBo getLoginUser(Integer id, Integer term) throws BusinessException {
        SysUserPo sysUserPo = sysUserService.getUser(id);
        LoginUserBo loginUserBo = ObjectUtils.objectMap(sysUserPo, LoginUserBo.class);
        List<SysTokenPo> sysTokenPoList = sysTokenService.getTokenByUserid(id);
        SysTokenPo sysTokenPo = null;
        for (SysTokenPo po : sysTokenPoList) {
            //if (po.getTerm() == term)
                sysTokenPo = po;
        }
        if (sysTokenPo == null)
            throw new BusinessException(Message.USER_NOT_EXIST);
        loginUserBo.setToken(sysTokenPo);
        loginUserBo.setProPhotoList(sysUserProPhotoService.getProfilePhoto(id));
        if (sysUserPo.getDprtmentId() != null) {
            SysDprtmentBo sysDprtmentBo = ObjectUtils.objectMap(sysDprtmentDao.selectOne(sysUserPo.getDprtmentId()), SysDprtmentBo.class);
            if (sysDprtmentBo.getCompanyId() != null)
                sysDprtmentBo.setCompany(sysCompanyDao.selectOne(sysDprtmentBo.getCompanyId()));
            loginUserBo.setDprtment(sysDprtmentBo);
        }
        return loginUserBo;
    }

    @Override
    public List<RoleDto> selectUserRole(Integer id) {
        List<RoleDto> dtos = sysUserDao.selectUserRole(id);
        return dtos;
    }

    @Override
    public String updateUserRole(Integer id, List<Integer> roleIds) {
        sysUserDao.deleteUserRole(id);
        if (roleIds != null && roleIds.size() > 0) {
            sysUserDao.insertUserRole(id, roleIds);
        }
        return "success";
    }
}
