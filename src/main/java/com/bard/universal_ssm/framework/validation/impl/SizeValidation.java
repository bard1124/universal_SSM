package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.Size;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class SizeValidation implements ColumnValidation<Size> {

	@Override
    public boolean isValid(Object value, Size annotation) {
        if (StringUtils.isEmpty(value))
            return true;
        if (annotation.min() == -1 && annotation.max() == -1)
            return true;
		int length = 0;
        if (ObjectUtils.isArray(value)) {
        	//数组
			length = ((Object[]) value).length;
        } else if(value instanceof List){
        	//列表
        	length = ((List<?>) value).size();
		} else {
			try {
				length = value.toString().getBytes(annotation.encode()).length;
			} catch (UnsupportedEncodingException e) {
			}
		}
		if (annotation.min() == -1)
			return length <= annotation.max();
		if (annotation.max() == -1)
			return length >= annotation.min();
		return length <= annotation.max() && length >= annotation.min();
    }

    @Override
    public String getMessage(String columnName, Size annotation) {
        if (annotation.min() == -1 && annotation.max() != -1)
            return String.format(Message.VALIDTION_NOT_MAX_SIZE, columnName, annotation.max());
        if (annotation.max() == -1 && annotation.min() != -1)
            return String.format(Message.VALIDTION_NOT_MIN_SIZE, columnName, annotation.min());
        return String.format(Message.VALIDTION_NOT_SIZE, columnName, annotation.min(), annotation.max());
    }

}
