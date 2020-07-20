package com.bard.universal_ssm.framework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 是否符合{pattern}的正则表达式，不符合的话返回{message}信息
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface Pattern {
	String pattern() default "";
	String message();
}
