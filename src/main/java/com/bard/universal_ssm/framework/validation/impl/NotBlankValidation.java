package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.NotBlank;

public class NotBlankValidation implements ColumnValidation<NotBlank> {

	@Override
	public boolean isValid(Object value, NotBlank annotation) {
		if (value==null)
			return true;
		return value.toString().trim().length()>0;
	}

	@Override
	public String getMessage(String columnName, NotBlank annotation) {
		return String.format(Message.VALIDTION_NOT_BLANK, columnName);
	}

}
