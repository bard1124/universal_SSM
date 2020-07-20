package com.bard.universal_ssm.service.impl;

import com.bard.universal_ssm.dao.SysOptionDao;
import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.utils.ObjectUtils;
import com.bard.universal_ssm.model.bo.SysOptionBo;
import com.bard.universal_ssm.model.dto.SysOptionDto;
import com.bard.universal_ssm.model.po.SysOptionPo;
import com.bard.universal_ssm.service.SysOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysOptionService")
public class SysOptionServiceImpl implements SysOptionService {

	@Autowired
	private SysOptionDao sysOptionDao;
	
	@Override
	public List<SysOptionBo> getOptions(SysOptionDto sysOptionDto) throws BusinessException {
		if(sysOptionDto.getRecursive() == null)
			sysOptionDto.setRecursive(true);
		return getOptionRec(sysOptionDto.getPid(), sysOptionDto.getRecursive(), sysOptionDto.getDepth(), 1);
	}
	
	/**
	 * 递归获取所有子节点信息
	 * @param pid
	 * @param recursive
	 * @param nowDepth
	 * @param maxDepth
	 * @return
	 */
	private List<SysOptionBo> getOptionRec(Integer pid, boolean recursive, Integer maxDepth, int nowDepth) {
		//判断递归深度
		if(maxDepth != null && recursive && maxDepth < nowDepth)
			return null;
		List<SysOptionPo> options = sysOptionDao.selectOptionByPid(pid);
		//判断是否存在子节点
		if(options == null || options.size() == 0)
			return null;
		List<SysOptionBo> rtnOptions = new ArrayList<SysOptionBo>();
		for(SysOptionPo option: options) {
			SysOptionBo od = ObjectUtils.objectMap(option, SysOptionBo.class);
			//如果是递归，则递归
			if(recursive)
				od.setChildren(getOptionRec(option.getId(), recursive, maxDepth, nowDepth + 1));
			rtnOptions.add(od);
		}
		return rtnOptions;
	}

}
