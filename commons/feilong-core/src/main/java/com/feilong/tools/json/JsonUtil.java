/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.tools.json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertySetStrategy;
import net.sf.json.xml.XMLSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.util.Validator;

/**
 * json 工具类 <br>
 * 依赖于
 * 
 * <pre>
 * <groupId>net.sf.json-lib</groupId>
 * <artifactId>json-lib</artifactId>
 * 
 * 如果要使用xml 部分功能,需要
 * <groupId>xom</groupId> 
 * <artifactId>xom</artifactId>
 * 
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 26, 2013 8:02:09 PM
 */
public final class JsonUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * Instantiates a new json util.
	 */
	private JsonUtil(){}

	/**
	 * 设置日期转换格式
	 */
	static{
		// 可转换的日期格式，即Json串中可以出现以下格式的日期与时间
		String[] formats = { DatePattern.commonWithTime, DatePattern.onlyTime, DatePattern.onlyDate };
		DateMorpher dateMorpher = new DateMorpher(formats);

		// 注册器
		MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
		morpherRegistry.registerMorpher(dateMorpher);
	}

	/**
	 * Format.
	 * 
	 * @param obj
	 *            the obj
	 * @return the string
	 */
	public static String format(Object obj){
		return format(obj, null);
	}

	/**
	 * Format.
	 * 
	 * @param obj
	 *            the obj
	 * @param excludes
	 *            the excludes 排除需要序列化成json的属性
	 * @return the string
	 */
	public static String format(Object obj,String[] excludes){
		JsonConfig jsonConfig = new JsonConfig();

		// 排除,避免循环引用 There is a cycle in the hierarchy!
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setIgnoreDefaultExcludes(true);

		// java.lang.ClassCastException: JSON keys must be strings
		// see http://feitianbenyue.iteye.com/blog/2046877
		jsonConfig.setAllowNonStringKeys(true);

		if (Validator.isNotNullOrEmpty(excludes)){
			jsonConfig.setExcludes(excludes);
		}
		String string = JsonUtil.toJSON(obj, jsonConfig).toString(4, 4);
		return string;
	}

	/**
	 * json串,转换成实体对象.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. {'name':'get','dateAttr':'2009-11-12'}<br>
	 *            可以是 json字符串,也可以是JSONObject<br>
	 *            Accepts JSON formatted strings, Maps, DynaBeans and JavaBeans. <br>
	 *            支持的格式有: {@link JSONObject#fromObject(Object, JsonConfig)}
	 * @param rootClass
	 *            e.g. Person.class
	 * @return the t
	 */
	public static <T> T toBean(Object json,Class<T> rootClass){
		return (T) toBean(json, rootClass, null);
	}

	/**
	 * 从json串转换成实体对象，并且实体集合属性存有另外实体Bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. {'data':[{'name':'get'},{'name':'set'}]}
	 * @param rootClass
	 *            e.g. MyBean.class
	 * @param classMap
	 *            e.g. classMap.put("data", Person.class)
	 * @return Object
	 */
	// TODO
	@SuppressWarnings("unchecked")
	public static <T> T toBean(Object json,Class<T> rootClass,Map<String, Class<?>> classMap){
		JSONObject jsonObject = JSONObject.fromObject(json);

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(rootClass);

		if (Validator.isNotNullOrEmpty(classMap)){
			jsonConfig.setClassMap(classMap);
		}
		return (T) toBean(jsonObject, jsonConfig);
	}

	/**
	 * json串,转换成对象.
	 * 
	 * @param json
	 *            the json
	 * @param jsonConfig
	 *            the json config
	 * @return the object
	 */
	public static Object toBean(Object json,JsonConfig jsonConfig){
		JSONObject jsonObject = JSONObject.fromObject(json);

		// Ignore missing properties with Json-Lib

		// 避免出现 Unknown property 'orderIdAndCodeMap' on class 'class
		// com.baozun.trade.web.controller.payment.result.command.PaymentResultEntity' 异常
		jsonConfig.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
		return JSONObject.toBean(jsonObject, jsonConfig);
	}

	// *****************************Array***************************************************************
	/**
	 * 把一个json数组串转换成普通数组.
	 * 
	 * @param json
	 *            e.g. ['get',1,true,null]
	 * @return Object[]
	 */
	public static Object[] toArray(String json){
		return JSONArray.fromObject(json).toArray();
	}

	/**
	 * 把一个json数组串,转换成实体数组.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. [{'name':'get'},{'name':'set'}]
	 * @param clazz
	 *            e.g. Person.class
	 * @return Object[]
	 */
	public static <T> T[] toArray(String json,Class<T> clazz){
		return toArray(json, clazz, null);
	}

	/**
	 * 把一个json数组串转换成实体数组，且数组元素的属性含有另外实例Bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. [{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}]
	 * @param clazz
	 *            e.g. MyBean.class
	 * @param classMap
	 *            e.g. classMap.put("data", Person.class)
	 * @return T[]
	 */
	public static <T> T[] toArray(String json,Class<T> clazz,Map<String, Class<?>> classMap){
		JSONArray jsonArray = JSONArray.fromObject(json);
		int size = jsonArray.size();

		@SuppressWarnings("unchecked")
		T[] t = (T[]) Array.newInstance(clazz, size);

		for (int i = 0; i < size; i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			t[i] = toBean(jsonObject, clazz, classMap);
		}
		return t;
	}

	// *****************************List***************************************************************
	/**
	 * 把一个json数组串转换成存放普通类型元素的集合.
	 * 
	 * @param json
	 *            e.g. ['get',1,true,null]
	 * @return List
	 */
	public static List<Object> toList(String json){
		JSONArray jsonArr = JSONArray.fromObject(json);
		int size = jsonArr.size();

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < size; i++){
			Object e = jsonArr.get(i);
			list.add(e);
		}
		return list;
	}

	/**
	 * 把一个json数组串转换成集合，且集合里存放的为实例Bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. [{'name':'get'},{'name':'set'}]
	 * @param clazz
	 *            the clazz
	 * @return List
	 */
	public static <T> List<T> toList(String json,Class<T> clazz){
		return toList(json, clazz, null);
	}

	/**
	 * 把一个json数组串转换成集合，且集合里的对象的属性含有另外实例Bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. [{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}]
	 * @param clazz
	 *            e.g. MyBean.class
	 * @param classMap
	 *            e.g. classMap.put("data", Person.class)
	 * @return List
	 */
	// TODO
	public static <T> List<T> toList(String json,Class<T> clazz,Map<String, Class<?>> classMap){
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<T> list = new ArrayList<T>();

		int size = jsonArray.size();
		for (int i = 0; i < size; i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			list.add((T) toBean(jsonObject, clazz, classMap));
		}
		return list;
	}

	// ********************************Map*****************************************************************************

	/**
	 * 把json对象串转换成map对象.
	 * 
	 * @param json
	 *            e.g. {'name':'get','int':1,'double',1.1,'null':null}
	 * @return Map
	 */
	public static Map<String, Object> toMap(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);

		Map<String, Object> map = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()){
			String key = keys.next();
			Object value = jsonObject.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 把json对象串转换成map对象，且map对象里存放的为其他实体Bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. {'data1':{'name':'get'}, 'data2':{'name':'set'}}
	 * @param clazz
	 *            e.g. Person.class
	 * @return Map
	 */
	public static <T> Map<String, T> toMap(String json,Class<T> clazz){
		return toMap(json, clazz, null);
	}

	/**
	 * 把json对象串转换成map对象，且map对象里存放的其他实体Bean还含有另外实体Bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            e.g. {'mybean':{'data':[{'name':'get'}]}}
	 * @param clazz
	 *            e.g. MyBean.class
	 * @param classMap
	 *            e.g. classMap.put("data", Person.class)
	 * @return Map
	 */
	// TODO
	public static <T> Map<String, T> toMap(String json,Class<T> clazz,Map<String, Class<?>> classMap){
		if (log.isDebugEnabled()){
			log.debug("in json:{}", json);
		}

		JSONObject jsonObject = JSONObject.fromObject(json);

		Map<String, T> map = new HashMap<String, T>();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()){
			String key = keys.next();
			// JSONObject jsonObject2 = jsonObject.getJSONObject(key);
			Object value = jsonObject.get(key);
			if (log.isDebugEnabled()){
				log.debug("key:{} value:{}", key, value);
			}
			map.put(key, (T) toBean(value, clazz, classMap));
		}
		return map;
	}

	/**
	 * 把实体Bean、Map对象、数组、列表集合转换成Json串.
	 * 
	 * @param obj
	 *            the obj
	 * @return the jSON
	 */
	public static JSON toJSON(Object obj){
		return toJSON(obj, null);
	}

	/**
	 * 把实体Bean、Map对象、数组、列表集合转换成Json串.
	 * 
	 * @param obj
	 *            the obj
	 * @param jsonConfig
	 *            the json config
	 * @return the jSON
	 */
	public static JSON toJSON(Object obj,JsonConfig jsonConfig){
		if (null == jsonConfig){
			jsonConfig = new JsonConfig();
			// 注册日期处理器
			// JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor(DatePattern.commonWithTime);
			// jsonConfig.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		}
		JSON json = null;

		// obj instanceof Collection || obj instanceof Object[]
		if (JSONUtils.isArray(obj)){
			json = JSONArray.fromObject(obj, jsonConfig);
		}else{
			json = JSONObject.fromObject(obj, jsonConfig);
		}
		return json;
	}

	/**
	 * 把json串、数组、集合(collection map)、实体Bean转换成XML<br>
	 * XMLSerializer API： http://json-lib.sourceforge.net/apidocs/net/sf/json/xml/XMLSerializer.html 具体实例请参考：<br>
	 * http://json-lib.sourceforge.net/xref-test/net/sf/json/xml/TestXMLSerializer_writes.html<br>
	 * http://json-lib.sourceforge.net/xref-test/net/sf/json/xml/TestXMLSerializer_writes.html
	 * 
	 * @param object
	 *            the object
	 * @return xml
	 */
	public static String objectToXML(Object object){
		return objectToXML(object, null);
	}

	/**
	 * Object to xml<br>
	 * 缺点:
	 * <ul>
	 * <li>不能去掉 < ?xml version="1.0" encoding="UTF-8"? ></li>
	 * <li>不能格式化输出</li>
	 * <li>对于空元素,不能输出 < additionalData> < /additionalData>,只能输出< additionalData/></li>
	 * </ul>
	 * 
	 * @param object
	 *            the object
	 * @param encoding
	 *            the encoding
	 * @return the string
	 */
	public static String objectToXML(Object object,String encoding){
		JSON json = toJSON(object);
		XMLSerializer xmlSerializer = new XMLSerializer();
		// xmlSerializer.setRootName("outputPaymentPGW");
		// xmlSerializer.setTypeHintsEnabled(true);
		// xmlSerializer.setTypeHintsCompatibility(true);
		// xmlSerializer.setSkipWhitespace(false);
		if (Validator.isNotNullOrEmpty(encoding)){
			return xmlSerializer.write(json, encoding);
		}
		return xmlSerializer.write(json);

	}

	/**
	 * XML转成json串.
	 * 
	 * @param xml
	 *            the xml
	 * @return String
	 */
	public static JSON xmlToJSON(String xml){
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		return json;
	}
}
