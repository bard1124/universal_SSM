package com.bard.universal_ssm.framework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 开启验证的注解
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target({PARAMETER})
@Documented
public @interface Validated {
}
