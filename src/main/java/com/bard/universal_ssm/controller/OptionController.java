package com.bard.universal_ssm.controller;

import com.bard.universal_ssm.model.dto.SysOptionDto;
import com.bard.universal_ssm.framework.annotation.SystemLog;
import com.bard.universal_ssm.framework.enumerate.OperationType;
import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.utils.ObjectUtils;
import com.bard.universal_ssm.service.SysOptionService;
import com.bard.universal_ssm.model.vo.SysOptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {
	
	@Autowired
	private SysOptionService optionService;
	
	@RequestMapping(method = RequestMethod.GET)
	@SystemLog(module="下拉选项", methods="获取子节点下拉选项", operType=OperationType.SELECT)
	public List<SysOptionVo> getOptions(SysOptionDto sysOptionDto) throws BusinessException{
		return ObjectUtils.listObjectMap(optionService.getOptions(sysOptionDto), SysOptionVo.class);
	}
	

}
