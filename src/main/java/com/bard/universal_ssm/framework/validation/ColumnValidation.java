package com.bard.universal_ssm.framework.validation;

import java.lang.annotation.Annotation;

/**
 * 用于验证字段数据是否合法
 * @author wei
 *
 * @param <A> 注解
 */
public interface ColumnValidation<A extends Annotation> {
	/**
	 * 验证数据的合法性
	 * @param value 数据
	 * @param annotation 注解
	 * @return
	 */
	public boolean isValid(Object value, A annotation);
	
	/**
	 * 验证失败时返回的信息
	 * @param columnName 字段名
	 * @param annotation 注解
	 * @return 错误信息
	 */
	public String getMessage(String columnName, A annotation);
}
