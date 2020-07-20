package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.utils.OptionUtils;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.Option;
import org.springframework.util.StringUtils;

public class OptionValidation implements ColumnValidation<Option> {

	@Override
	public boolean isValid(Object value, Option annotation) {
		if (StringUtils.isEmpty(value))
			return true;
		return OptionUtils.isOptionValue(value.toString(), annotation.pid(), annotation.depth());
	}

	@Override
	public String getMessage(String columnName, Option annotation) {
		return String.format(Message.VALIDTION_OPTION, columnName);
	}

}
