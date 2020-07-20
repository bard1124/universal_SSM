package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.Pattern;
import org.springframework.util.StringUtils;


public class PatternValidation implements ColumnValidation<Pattern> {

	@Override
	public boolean isValid(Object value, Pattern annotation) {
		if (StringUtils.isEmpty(value))
			return true;
		return java.util.regex.Pattern.compile(annotation.pattern()).matcher(value.toString()).matches();
	}

	@Override
	public String getMessage(String columnName, Pattern annotation) {
		return annotation.message();
	}

}
