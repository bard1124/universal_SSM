package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.NotNull;

public class NotNullValidation implements ColumnValidation<NotNull> {

	@Override
	public boolean isValid(Object value, NotNull annotation) {
		return value != null;
	}

	@Override
	public String getMessage(String columnName, NotNull annotation) {
		return String.format(Message.VALIDTION_NOT_NULL, columnName);
	}

}
