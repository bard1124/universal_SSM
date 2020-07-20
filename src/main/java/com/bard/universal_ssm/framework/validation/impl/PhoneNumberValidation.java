package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.PhoneNumber;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class PhoneNumberValidation implements ColumnValidation<PhoneNumber> {
	
	private static final Pattern REGEX_MOBILE = Pattern.compile("(13|14|15|17|18|19)[0-9]{9}");

	@Override
	public boolean isValid(Object value, PhoneNumber annotation) {
		if (StringUtils.isEmpty(value))
			return true;
		return REGEX_MOBILE.matcher(value.toString()).matches();
	}

	@Override
	public String getMessage(String columnName, PhoneNumber annotation) {
		return String.format(Message.VALIDTION_NOT_PHONE, columnName);
	}

}
