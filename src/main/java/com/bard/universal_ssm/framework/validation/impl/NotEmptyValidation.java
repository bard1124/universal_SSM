package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.NotEmpty;

public class NotEmptyValidation implements ColumnValidation<NotEmpty> {

	@Override
	public boolean isValid(Object value, NotEmpty annotation) {
		if (value==null)
			return true;
		return value.toString().length()>0;
	}

	@Override
	public String getMessage(String columnName, NotEmpty annotation) {
		return String.format(Message.VALIDTION_NOT_EMPTY, columnName);
	}

}
