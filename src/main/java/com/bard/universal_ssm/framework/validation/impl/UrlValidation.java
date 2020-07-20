package com.bard.universal_ssm.framework.validation.impl;

import com.bard.universal_ssm.framework.constants.Message;
import com.bard.universal_ssm.framework.enumerate.ProtocolType;
import com.bard.universal_ssm.framework.validation.ColumnValidation;
import com.bard.universal_ssm.framework.validation.annotation.Url;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class UrlValidation implements ColumnValidation<Url> {

    private static final Pattern URL_PATTERN = Pattern.compile("(https|http|ftp)://"
    		+ "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+");

    @Override
    public boolean isValid(Object value, Url annotation) {
        if (StringUtils.isEmpty(value))
            return true;
        if(!URL_PATTERN.matcher(value.toString()).matches())
        	return false;
        for (ProtocolType prot : annotation.value()){
        	switch(prot) {
        	case FTP:
        		if(value.toString().startsWith("ftp:"))
        			return true;
        	case HTTP:
        		if(value.toString().startsWith("http:"))
        			return true;
        	case HTTPS:
        		if(value.toString().startsWith("https:"))
        			return true;
        	}
        }
        return false;
    }

    @Override
    public String getMessage(String columnName, Url annotation) {
        return String.format(Message.VALIDTION_NOT_URL, columnName);
    }

}
