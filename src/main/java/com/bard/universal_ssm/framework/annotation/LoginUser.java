package com.bard.universal_ssm.framework.annotation;

import java.lang.annotation.*;

/**
 * 登入用户
 * @author wei
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
