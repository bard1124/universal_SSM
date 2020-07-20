package com.bard.universal_ssm.framework.utils;

import com.bard.universal_ssm.framework.annotation.OptionValue;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ObjectUtils {
/*
    private static SysOptionDao sysOptionDao;
    
    @Autowired
    public ObjectUtils(SysOptionDao sysOptionDao) {
    	ObjectUtils.sysOptionDao = sysOptionDao;
    }
	*/
	/**
	 * 对象数据拷贝
	 * @param source源对象
	 * @param target目标对象
	 */
	public static void objectMap(Object source, Object target) {
		if(source == null || target == null)
			return;
		Map<String, Field> sourceMap = ReflectUtil.getAllField(source.getClass());
		Map<String, Field> targetMap = ReflectUtil.getAllField(target.getClass());
		for (String key : targetMap.keySet()) {
			Field targetField = targetMap.get(key);
			targetField.setAccessible(true);
			if (sourceMap.containsKey(key)){
				//字段名相同的场合
				Field sourceField = sourceMap.get(key);
				sourceField.setAccessible(true);
				try {
					//将数值拷贝到另一个对象中
					targetField.set(target, dataMap(sourceField.get(source), targetField.getGenericType(), sourceField, targetField));
				} catch (IllegalArgumentException | IllegalAccessException e) {
				}					
			}
			//如果有OptionValue注解，则返回下拉值
			OptionValue optionValue = targetField.getAnnotation(OptionValue.class);
			if(optionValue != null && !"".equals(optionValue.value()) && String.class.isAssignableFrom(targetField.getType())) {
				try {
					Field field = source.getClass().getDeclaredField(optionValue.value());
					field.setAccessible(true);
					Object val = field.get(source);
					if(val == null)
						continue;
					targetField.set(target, getOptionText(val.toString(), optionValue.pid(), optionValue.depth()));
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					continue;
				}
			}
		}
	}
	
/*	*//**
	 * 将分页对象中的对象转换成另一个对象
	 * @param source
	 * @param type
	 * @return
	 *//*
	public static <T extends Object> PageInfo<T> pageInfoMap(PageInfo<?> source, Class<T> type) {
		PageInfo<T> target = new PageInfo<T>();
		objectMap(source, target);
		target.setList(listObjectMap(source.getList(), type));
		return target;
	}
	*/
	/**
	 * 将List中的对象转换成另一个对象
	 * @param source
	 * @param type
	 * @return
	 */
	public static <T extends Object> List<T> listObjectMap(List<?> source, Class<T> type) {
		if(source == null)
			return null;
		List<T> tl = new ArrayList<T>();
		for(Object obj: source) {
			tl.add(objectMap(obj, type));
		}
		return tl;
	}

	/**
	 * 生产目标对象并将源对象中的数据拷贝进去
	 * @param data
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T objectMap(Object data, Class<T> type) {
		try {
			//构造一个目标对象
			if(data == null)
				return null;
			if(data.getClass().isAssignableFrom(type))
				return (T) data;
			T obj = type.getConstructor().newInstance();
			objectMap(data, obj);
			return obj;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将对象中的List对象展开到每一个对象中
	 * @param data
	 * @param type
	 * @param listKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> List<T> objectMapOneToMore(Object data, Class<T> type, String listKey) {
		List<T> rtn = new ArrayList<T>();
		Map<String, Field> sourceMap = ReflectUtil.getAllField(data.getClass());
		Map<String, Field> targetMap = ReflectUtil.getAllField(type);
		Field listField = sourceMap.get(listKey);
		if(listField != null) {
			if(Iterable.class.isAssignableFrom(listField.getType())) {
				try {
					listField.setAccessible(true);
					//获取List对象
					Iterable<Object> list = (Iterable<Object>) listField.get(data);
					for(Object listObj: list) {
						//遍历list对象
						T targetObj = type.getConstructor().newInstance();
						Map<String, Field> listMap = ReflectUtil.getAllField(listObj.getClass());
						for(String key: targetMap.keySet()) {
							Object sourceTmpOBj = null;
							Field sourceTmpField = null;
							Field targetField = targetMap.get(key);
							if(listMap.containsKey(key)) {
								sourceTmpOBj = listObj;
								sourceTmpField = listMap.get(key);
							} else if(sourceMap.containsKey(key)) {
								sourceTmpOBj = data;
								sourceTmpField = sourceMap.get(key);
							} else {
								continue;
							}
							targetField.setAccessible(true);
							sourceTmpField.setAccessible(true);
							if(sourceTmpOBj != null && sourceTmpField != null) {
								targetField.set(targetObj, dataMap(sourceTmpField.get(sourceTmpOBj), targetField.getGenericType(), sourceTmpField, targetField));
							}
						}
						rtn.add(targetObj);
					}
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					return null;
				}
			}
		} else {
			T targetObj = objectMap(data, type);
			if(targetObj != null)
				rtn.add(targetObj);
			else
				return null;
		}
		return rtn;
	}

	/**
	 * 将List对象根据keys进行分组输出
	 * @param data
	 * @param type
	 * @param keys
	 * @return
	 */
	public static <T extends Object> List<T> objectMapGroup(Iterable<?> data, Class<T> type, String[] keys) {
		try {
			List<T> targetListObj = new ArrayList<T>();
			Object[] compareKeysOld = null;
			Object[] compareKeysNew = null;
			T obj = null;
			for(Object sourceObj: data) {
				Map<String, Field> sourceMap = ReflectUtil.getAllField(sourceObj.getClass());
				compareKeysNew = getCompareKeys(sourceMap, sourceObj, keys);
				if(!compare(compareKeysOld, compareKeysNew) || keys.length == 0) {
					obj = type.getConstructor().newInstance();
					targetListObj.add(obj);
				}
				Map<String, Field> targetMap = ReflectUtil.getAllField(obj.getClass());
				for(String key: targetMap.keySet()) {
					if(sourceMap.containsKey(key)) {
						Field sourceField = sourceMap.get(key);
						sourceField.setAccessible(true);
						Field targetField = targetMap.get(key);
						targetField.setAccessible(true);
						targetField.set(obj, dataMap(sourceField.get(sourceObj), targetField.getGenericType(), sourceField, targetField));
					} else {
						Field targetField = targetMap.get(key);
						targetField.setAccessible(true);
						if(List.class.isAssignableFrom(targetField.getType())) {
							@SuppressWarnings("unchecked")
							List<Object> list = (List<Object>) targetField.get(obj);
							if(list == null) {
								list = new ArrayList<Object>();
								targetField.set(obj, list);
							}
							Type genericClazz = ((ParameterizedType) targetField.getGenericType()).getActualTypeArguments()[0];
							Object targetObj = ((Class<?>) genericClazz).getConstructor().newInstance();
							Map<String, Field> targetListMap = ReflectUtil.getAllField(targetObj.getClass());
							for (String listKey : sourceMap.keySet()) {
								if (targetListMap.containsKey(listKey)){
									Field sourceField = sourceMap.get(listKey);
									sourceField.setAccessible(true);
									Field targetListField = targetListMap.get(listKey);
									targetListField.setAccessible(true);
									targetListField.set(targetObj, dataMap(sourceField.get(sourceObj), targetListField.getGenericType(), sourceField, targetListField));					
								}
							}
							list.add(targetObj);
						}
					}
						
				}
				compareKeysOld = compareKeysNew;
			}
			return targetListObj;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 对象数组比较
	 * @param compareKeysOld
	 * @param compareKeysNew
	 * @return
	 */
	private static boolean compare(Object[] compareKeysOld, Object[] compareKeysNew) {
		if(compareKeysOld == null || compareKeysNew == null)
			return false;
		for(int i = 0; i < compareKeysNew.length; i++) {
			if(!compareObject(compareKeysOld[i], compareKeysNew[i]))
				return false;
		}
		return true;
	}
	
	/**
	 * 对象比较
	 * @param oldObj
	 * @param newObj
	 * @return
	 */
	private static boolean compareObject(Object oldObj, Object newObj) {
		if(oldObj == null) {
			if(newObj == null)
				return true;
			else
				return false;
		} else {
			if(newObj == null)
				return false;
			else
				return oldObj.equals(newObj);
		}
	}
	
	/**
	 * 获取值
	 * @param fieldMap
	 * @param obj
	 * @param keys
	 * @return
	 */
	private static Object[] getCompareKeys(Map<String, Field> fieldMap, Object obj, String[] keys) {
		Object[] compareKeys = new Object[keys.length];
		for(int i = 0; i < keys.length; i++) {
			Field f = fieldMap.get(keys[i]);
			if(f == null)
				continue;
			f.setAccessible(true);
			try {
				compareKeys[i] = f.get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				continue;
			}
		}
		return compareKeys;
	}
	
	/**
	 * 根据类型映射值
	 * @param source
	 * @param type
	 * @param sourceField
	 * @param targetField
	 * @return
	 */
	private static Object dataMap(Object source, Type type, Field sourceField, Field targetField) {
		Class<?> targetType = null;
    	if(type instanceof ParameterizedType)
    		targetType = (Class<?>) ((ParameterizedType) type).getRawType();
    	else
    		targetType = (Class<?>) type;
		if(source == null)
			return null;
		//假如类型相同，则直接返回
		if(targetType.isAssignableFrom(source.getClass())) {
			if(source instanceof List && List.class.isAssignableFrom(targetType)) {
				return listObjectMap((List<?>) source, (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0]);
			}
			return source;
		}
		try {
			//如果基本类型和对象类型相同则直接返回
			if(source.getClass().getField("TYPE").get(null).equals(targetType)) {
				return source;
			}
		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			
		}
//		//如果是Date类型，则根据DateFormat转换格式
//		if (Date.class.isAssignableFrom(source.getClass())) {
//			if (String.class.isAssignableFrom(targetType)) {
//				DateFormat df = targetField.getAnnotation(DateFormat.class);
//				if(df != null) {
//					SimpleDateFormat sdm = new SimpleDateFormat(df.value());
//					return sdm.format((Date) source);
//				}
//			}
//		}
//		//如果是Date类型，则根据DateFormat转换格式
//		if (Date.class.isAssignableFrom(targetType)) {
//			if (String.class.isAssignableFrom(source.getClass())) {
//				DateFormat df = sourceField.getAnnotation(DateFormat.class);
//				SimpleDateFormat sdm = new SimpleDateFormat(df.value());
//				try {
//					return sdm.parse((String) source);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//		}
    	if(Number.class.isAssignableFrom(source.getClass())) {
    		if(Byte.class.isAssignableFrom(targetType))
    			return ((Number) source).byteValue();
    		if(Short.class.isAssignableFrom(targetType))
    			return ((Number) source).shortValue();
    		if(Integer.class.isAssignableFrom(targetType))
    			return ((Number) source).intValue();
    		if(Long.class.isAssignableFrom(targetType))
    			return ((Number) source).longValue();
    		if(Float.class.isAssignableFrom(targetType))
    			return ((Number) source).floatValue();
    		if(Double.class.isAssignableFrom(targetType))
    			return ((Number) source).doubleValue();
    		if(BigDecimal.class.isAssignableFrom(targetType))
    			return new BigDecimal(source.toString());
    		if(String.class.isAssignableFrom(targetType))
    			return source.toString();
    	} else if(String.class.isAssignableFrom(source.getClass())) {
    		try {
	    		if(Byte.class.isAssignableFrom(targetType))
	    			return Byte.valueOf((String) source);
	    		if(Short.class.isAssignableFrom(targetType))
	    			return Short.valueOf((String) source);
	    		if(Integer.class.isAssignableFrom(targetType))
	    			return Integer.valueOf((String) source);
	    		if(Long.class.isAssignableFrom(targetType))
	    			return Long.valueOf((String) source);
	    		if(Float.class.isAssignableFrom(targetType))
	    			return Float.valueOf((String) source);
	    		if(Double.class.isAssignableFrom(targetType))
	    			return Double.valueOf((String) source);
	    		if(BigDecimal.class.isAssignableFrom(targetType))
	    			return new BigDecimal((String) source);
    		} catch (Exception e) {
    	    	return null;
    		}
    		if(String.class.isAssignableFrom(targetType))
    			return source;
    	} else {
    		return objectMap(source, targetType);
    	}
		return null;
	}
	
	/**
	 * 根据value获得text
	 * @param val
	 * @param pid
	 * @param depth
	 * @return
	 */
	private static String getOptionText(String val, int pid, int depth) {
		return getOptionText(val, pid, depth, new String[100], 1);
	}
	
	/**
	 * 根据value获得text
	 * @param val
	 * @param pid
	 * @param depth
	 * @param cache
	 * @param depthNow
	 * @return
	 */
	private static String getOptionText(String val, int pid, int depth, String[] cache, int depthNow) {
		if(val == null)
			return null;
/*		for(SysOptionPo sysOptionPo: sysOptionDao.selectOptionByPid(pid)) {
			cache[depthNow - 1] = sysOptionPo.getValue();
			if(val.equals(sysOptionPo.getValue())) {
				if(depth < 1)
					return sysOptionPo.getText();
				else
					return cache[depth - 1];
			}
			String t = getOptionText(val, sysOptionPo.getId(), depth, cache, depthNow + 1);
			if(t != null)
				return t;
		}*/
		return null;
	}
}
