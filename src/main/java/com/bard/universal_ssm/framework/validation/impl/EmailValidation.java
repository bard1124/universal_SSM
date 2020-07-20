package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.Email;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class EmailValidation implements ColumnValidation<Email> {

	private static final Pattern REGEX_EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$");

	@Override
	public boolean isValid(Object value, Email annotation) {
		if (StringUtils.isEmpty(value))
			return true;
		return REGEX_EMAIL.matcher(value.toString()).matches();
	}

	@Override
	public String getMessage(String columnName, Email annotation) {
		return String.format(Message.VALIDTION_NOT_EMAIL, columnName);
	}

}
