package com.bard.universal_ssm.framework.utils;

import com.bard.universal_ssm.dao.SysOptionDao;
import com.bard.universal_ssm.model.po.SysOptionPo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OptionUtils {
	private static SysOptionDao sysOptionDao;
	
	@Autowired
	public OptionUtils(SysOptionDao sysOptionDao) {
		OptionUtils.sysOptionDao = sysOptionDao;
	}
	
	public static boolean isOptionValue(String value, int pid, int depth) {
		return isOptionValue(value, pid, depth, 1);
	}
	
	private static boolean isOptionValue(String value, int pid, int depth, int now) {
		if(depth != -1 && now > depth)
			return false;
		List<SysOptionPo> sysOptionPoList = sysOptionDao.selectOptionByPid(pid);
		for(SysOptionPo sysOptionPo: sysOptionPoList) {
			if(sysOptionPo.getValue().equals(value) && (depth == -1 || depth == now))
				return true;
			if(isOptionValue(value, sysOptionPo.getId(), depth, now + 1))
				return true;
		}
		return false;
	}
}
