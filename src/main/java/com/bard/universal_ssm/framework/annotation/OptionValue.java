package com.bard.universal_ssm.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface OptionValue {
	String value() default "";
	int pid() default 0;
	int depth() default -1;
}
