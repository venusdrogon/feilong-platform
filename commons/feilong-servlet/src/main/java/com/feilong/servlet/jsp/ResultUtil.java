/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.servlet.jsp;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import com.feilong.commons.core.util.BeanUtil;
import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.ReflectUtil;
import com.feilong.commons.core.util.Validator;

/**
 * jdbc Result的工具类
 * 
 * @author 金鑫 2010-7-9 上午11:55:22
 * @since 1.0
 */
public class ResultUtil{

	/**
	 * 将result 转成任意的单个的 java bean
	 * 
	 * <pre>
	 * result 数据必须只有一个.
	 * 可变参数不填,则全部填充
	 * </pre>
	 * 
	 * @param result
	 *            result
	 * @param clz
	 *            clz
	 * @param fieldNames
	 *            字段参数
	 * @return java bean
	 */
	public static Object convertResultToObjectOneBean(Result result,Class clz,String...fieldNames){
		if (isEmpty(result)){
			return null;
		}
		int rowCount = result.getRowCount();
		// 多条数据不能实用这个方法
		if (rowCount > 1){
			throw new IllegalArgumentException("result 数据" + rowCount + "条");
		}
		Object bean = null;
		String className = clz.getName();
		SortedMap<Object, Object>[] sortedMaps = result.getRows();
		SortedMap<Object, Object> sortedMap = sortedMaps[0];
		// 实例化
		bean = ReflectUtil.newInstance(className);
		// 是否取字段
		boolean isGetFileds = Validator.isNotNullOrEmpty(fieldNames);
		if (!isGetFileds){
			fieldNames = ReflectUtil.getDeclaredFieldNames(clz);
		}
		// ***********************************************************************************
		Field field = null;
		// 数据库中字段值
		Object sqlValue = null;
		for (String fieldName : fieldNames){
			field = ReflectUtil.getDeclaredField(clz, fieldName);
			if (null != field){
				String name = "";
				Object value = "";
				sqlValue = sortedMap.get(fieldName);
				// 是否是date类型
				if (Date.class.isAssignableFrom(field.getType())){
					// bean 里面的date类型字段需要先实例化
					name = fieldName + ".time";
					value = ((Date) sqlValue).getTime();
					BeanUtil.setProperty(bean, name, value, false);
				}else{
					name = fieldName;
					value = sqlValue;
					BeanUtil.setProperty(bean, name, value, true);
				}
			}
		}
		return bean;
	}

	/**
	 * 将Result转成list
	 * 
	 * @param result
	 *            结果集
	 * @param clz
	 *            类名
	 * @param fileds
	 *            字段可变参数
	 * @return 将Result转成list
	 */
	@SuppressWarnings("unchecked")
	public static List convertResultToList(Result result,Class clz,String...fileds){
		if (isEmpty(result)){
			return null;
		}
		List list = new LinkedList();
		SortedMap<Object, Object>[] sortedMaps = result.getRows();
		// 是否取字段
		boolean isGetFileds = Validator.isNotNullOrEmpty(fileds);
		Object bean;
		String className = clz.getName();
		for (SortedMap<Object, Object> sortedMap : sortedMaps){
			// 实例化
			bean = ReflectUtil.newInstance(className);
			if (isGetFileds){
				for (String filed : fileds){
					BeanUtil.setProperty(bean, filed, sortedMap.get(filed), true);
				}
			}
			list.add(bean);
		}
		return list;
	}

	/**
	 * 将result 单个sortedMap 值取出赋予bean(fileds字段区分大小写,必须和bean 里面的字段一一对应)
	 * 
	 * <pre>
	 * 自动转换类型
	 * </pre>
	 * 
	 * @param sortedMap
	 *            sortedMap
	 * @param bean
	 *            bean
	 * @param fileds
	 *            字段
	 */
	public static void getSortedMapValueToBean(SortedMap sortedMap,Object bean,String...fileds){
		if (Validator.isNotNullOrEmpty(sortedMap) && Validator.isNotNullOrEmpty(bean) && Validator.isNotNullOrEmpty(fileds)){
			String value = "";
			for (String filed : fileds){
				value = getSortedMapValueByKeyWithTrim(sortedMap, filed);
				BeanUtil.setProperty(bean, filed, value, true);
			}
		}
	}

	/**
	 * 将result 单个sortedMap 值取出赋予bean(fileds字段不区分大小写,自动转换成规范化的字段)
	 * 
	 * <pre>
	 * 自动转换类型
	 * </pre>
	 * 
	 * @param sortedMap
	 *            sortedMap
	 * @param bean
	 *            bean
	 * @param fileds
	 *            字段
	 */
	public static void getSortedMapValueToBeanAutoChangeRegulateFiled(SortedMap sortedMap,Object bean,String...fileds){
		if (Validator.isNotNullOrEmpty(fileds)){
			for (int i = 0, j = fileds.length; i < j; ++i){
				fileds[i] = ReflectUtil.convertNameToPropertyName(fileds[i]);
			}
		}
		getSortedMapValueToBean(sortedMap, bean, fileds);
	}

	/**
	 * 判断Result不是空的
	 * 
	 * @param result
	 *            result
	 * @return 不是空返回true
	 */
	public static boolean isNotEmpty(Result result){
		return !isEmpty(result);
	}

	/**
	 * 判断Result是否是空的
	 * 
	 * @param result
	 *            result
	 * @return 空返回true
	 */
	public static boolean isEmpty(Result result){
		if (null == result){
			return true;
		}else if (result.getRowCount() == 0){
			return true;
		}
		return false;
	}

	/**
	 * 获得result结果唯一值
	 * 
	 * @param result
	 * @return 获得result结果唯一值
	 */
	public static Object getUniqueResult(Result result){
		if (ResultUtil.isEmpty(result)){
			return null;
		}
		SortedMap sortedMap = result.getRows()[0];
		Object object = sortedMap.get(result.getColumnNames()[0]);
		return object;
	}

	// **********************************
	/**
	 * 获得result row的特定字段的值(key,不区分大小写)
	 * 
	 * @param sortedMap
	 *            SortedMap
	 * @param key
	 *            字段名称
	 * @return 获得result row的特定字段的值
	 */
	public static Object getSortedMapValueByKey(SortedMap sortedMap,Object key){
		return sortedMap.get(key);
	}

	/**
	 * 获得result row的特定字段的值,并去除空格(key,不区分大小写)
	 * 
	 * @param sortedMap
	 *            SortedMap
	 * @param key
	 *            filedName
	 * @return 获得result row的特定字段的值,并去除空格
	 */
	public static String getSortedMapValueByKeyWithTrim(SortedMap sortedMap,Object key){
		return ObjectUtil.trim(getSortedMapValueByKey(sortedMap, key));
	}

	/**
	 * 获得result row的特定字段的值,并转成Integer类型(key,不区分大小写)
	 * 
	 * @param sortedMap
	 *            SortedMap
	 * @param key
	 *            filedName
	 * @return 获得result row的特定字段的值,并转成Integer类型
	 */
	public static Integer getSortedMapValueByKeyAndToInteger(SortedMap sortedMap,Object key){
		Object value = getSortedMapValueByKey(sortedMap, key);
		return ObjectUtil.toInteger(value);
	}
}
