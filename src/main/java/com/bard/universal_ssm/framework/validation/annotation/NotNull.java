package com.bard.universal_ssm.framework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 不为null
 * @author wei
 *
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface NotNull {
}
