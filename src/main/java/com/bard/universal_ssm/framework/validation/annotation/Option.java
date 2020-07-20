package com.bard.universal_ssm.framework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 是否为合法的下拉框的选项
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Option {
	int pid() default 0;
	int depth() default -1;
}
