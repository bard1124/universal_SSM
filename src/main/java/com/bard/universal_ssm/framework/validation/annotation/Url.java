package com.bard.universal_ssm.framework.validation.annotation;


import com.bard.universal_ssm.framework.enumerate.ProtocolType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.bard.universal_ssm.framework.enumerate.ProtocolType.HTTP;
import static com.bard.universal_ssm.framework.enumerate.ProtocolType.HTTPS;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 是否为Url
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface Url {
	ProtocolType[] value() default {HTTP, HTTPS};
}
