package com.bard.universal_ssm.framework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 根据字符编码{encode}验证字段长度，{min}和{max}参数为-1的情况下不验证其合法性
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface Size {
	int min() default -1;
	int max() default -1;
	String encode() default "utf-8";
}
