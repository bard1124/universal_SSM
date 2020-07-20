package com.bard.universal_ssm.framework.validation;

import com.bard.universal_ssm.framework.exception.ValidationException;
import com.bard.universal_ssm.framework.utils.ReflectUtil;
import com.bard.universal_ssm.framework.validation.annotation.*;
import com.bard.universal_ssm.framework.validation.impl.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validation {
	@SuppressWarnings("rawtypes")
	public static Map<Class<?>, ColumnValidation> columnValidationMap = new HashMap<Class<?>, ColumnValidation>() {
		private static final long serialVersionUID = -3742238045068127133L;
		{
			put(Email.class, new EmailValidation());
			put(GreaterOrEqual.class, new GreaterOrEqualValidation());
			put(Greater.class, new GreaterValidation());
			put(LessOrEqual.class, new LessOrEqualValidation());
			put(Less.class, new LessValidation());
			put(NotBlank.class, new NotBlankValidation());
			put(NotEmpty.class, new NotEmptyValidation());
			put(NotNull.class, new NotNullValidation());
			put(Pattern.class, new PatternValidation());
			put(PhoneNumber.class, new PhoneNumberValidation());
			put(Size.class, new SizeValidation());
			put(Url.class, new UrlValidation());
			put(Option.class, new OptionValidation());
		}
	};
	public static void verify(Object obj) throws ValidationException {
		List<String> messageList = new ArrayList<String>();
		verify(obj, messageList);
		if(messageList.size() != 0)
			throw new ValidationException(messageList);
	}
	
	@SuppressWarnings("unchecked")
	private static void verify(Object obj, List<String> messageList) throws ValidationException {
		if(obj instanceof List) {
			for(Object lObj: (List<?>) obj) {
				verify(lObj, messageList);
			}
		} else {
			Map<String, Field> fieldMap = ReflectUtil.getAllField(obj.getClass());
			br: for (String key : fieldMap.keySet()) {
				Field field = fieldMap.get(key);
				field.setAccessible(true);
				Object value = null;
				try {
					value = field.get(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {}
				for(Annotation ann: field.getDeclaredAnnotations()) {
					for(Class<?> cls: columnValidationMap.keySet()) {
						if(cls.isAssignableFrom(ann.getClass())) {
							@SuppressWarnings("rawtypes")
							ColumnValidation cvd = columnValidationMap.get(cls);
							if(!cvd.isValid(value, ann)) {
								messageList.add(cvd.getMessage(getColunmName(field), ann));
								continue br;
							}
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * 获取字段名
	 * @param field
	 * @return
	 */
	private static String getColunmName(Field field) {
		Column column = field.getDeclaredAnnotation(Column.class);
		if(column == null)
			return field.getName();
		else
			return column.value();
	}
}
