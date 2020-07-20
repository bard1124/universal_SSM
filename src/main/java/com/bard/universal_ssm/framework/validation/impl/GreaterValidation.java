package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.Greater;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class GreaterValidation implements ColumnValidation<Greater> {

	@Override
	public boolean isValid(Object value, Greater annotation) {
		if (StringUtils.isEmpty(value))
			return true;

		return  new BigDecimal(value.toString()).compareTo(new BigDecimal(annotation.value()))==1;
	}

	@Override
	public String getMessage(String columnName, Greater annotation) {
		return String.format(Message.VALIDTION_NOT_MAX, columnName,annotation.value());
	}

}
