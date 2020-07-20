package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.GreaterOrEqual;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class GreaterOrEqualValidation implements ColumnValidation<GreaterOrEqual> {

	@Override
	public boolean isValid(Object value,GreaterOrEqual annotation) {
		if (StringUtils.isEmpty(value))
			return true;

		 return new BigDecimal(value.toString()).compareTo(new BigDecimal(annotation.value()))>-1;

	}

	@Override
	public String getMessage(String columnName, GreaterOrEqual annotation) {
		return String.format(Message.VALIDTION_NOT_DECIMALMAX, columnName,annotation.value());
	}

}
