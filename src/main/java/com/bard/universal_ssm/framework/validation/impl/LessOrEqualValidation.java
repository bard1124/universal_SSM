package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.LessOrEqual;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class LessOrEqualValidation implements ColumnValidation<LessOrEqual> {

	@Override
	public boolean isValid(Object value, LessOrEqual annotation) {
		if (StringUtils.isEmpty(value))
			return true;
		return  new BigDecimal(value.toString()).compareTo(new BigDecimal(annotation.value()))<1;
	}

	@Override
	public String getMessage(String columnName, LessOrEqual annotation) {
		return String.format(Message.VALIDTION_NOT_DECIMALMIN, columnName,annotation.value());
	}

}
