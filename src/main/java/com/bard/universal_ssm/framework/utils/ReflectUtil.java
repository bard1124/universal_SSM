package com.bard.universal_ssm.framework.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtil {
	public static Map<String, Field> getAllField(Class<?> cls) {
		Map<String, Field> map = new HashMap<String, Field>();
		getAllField(cls, map);
		return map;
	}
	
	private static void getAllField(Class<?> cls, Map<String, Field> map) {
    	if(cls.isAssignableFrom(Object.class))
    		return;
    	for(Field field: cls.getDeclaredFields()) {
    		String name = field.getName();
    		if(!map.containsKey(name))
    			map.put(name, field);
    	}
    	getAllField(cls.getSuperclass(), map);
	}
}
