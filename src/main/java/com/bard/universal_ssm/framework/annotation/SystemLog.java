package com.bard.universal_ssm.framework.annotation;

import com.bard.universal_ssm.framework.enumerate.OperationType;

import java.lang.annotation.*;

/**
 * Log信息
 * @author xinwei.wang
 *
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	/** 模块名 */
	String module()  default "";
	/** 方法名 */
    String methods()  default "";
	/** 操作类型 */
    OperationType operType() default OperationType.SELECT;
}
