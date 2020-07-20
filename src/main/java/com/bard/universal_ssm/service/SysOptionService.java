package com.bard.universal_ssm.service;

import com.bard.universal_ssm.model.bo.SysOptionBo;
import com.bard.universal_ssm.model.dto.SysOptionDto;
import com.bard.universal_ssm.framework.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysOptionService {
	public List<SysOptionBo> getOptions(SysOptionDto sysOptionDto) throws BusinessException;
}
