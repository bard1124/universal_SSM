package com.bard.universal_ssm.controller;

import com.bard.universal_ssm.framework.annotation.LoginUser;
import com.bard.universal_ssm.framework.annotation.SystemLog;
import com.bard.universal_ssm.framework.enumerate.OperationType;
import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.utils.ObjectUtils;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.dto.AuthChangePasswdDto;
import com.bard.universal_ssm.model.dto.AuthDto;
import com.bard.universal_ssm.model.vo.LoginUserVo;
import com.bard.universal_ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @SystemLog(module="用户验证", methods="用户登陆系统", operType= OperationType.LOGIN)
    public LoginUserVo userLogin(@RequestBody @Validated AuthDto authDto) throws BusinessException {
        return ObjectUtils.objectMap(authService.login(authDto), LoginUserVo.class);
    }

    @RequestMapping(value = "/check-password", method = RequestMethod.GET)
    @SystemLog(module="用户验证", methods="检查用户密码", operType=OperationType.SELECT)
    public Boolean checkPassword(@Validated AuthDto authDto, @LoginUser LoginUserBo loginUserBo) throws BusinessException{
        return authService.checkPassword(authDto, loginUserBo);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.PUT)
    @SystemLog(module="用户验证", methods="修改用户密码", operType=OperationType.UPDATE)
    public void changePassword(@RequestBody @Validated AuthChangePasswdDto authChangePasswdDto, @LoginUser LoginUserBo loginUserBo) throws BusinessException{
        authService.updatePassword(authChangePasswdDto, loginUserBo);
    }
}
