package com.bard.universal_ssm.framework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 是否大于等于{value}
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface GreaterOrEqual {
	String value() default "";
}
