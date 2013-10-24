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
package com.feilong.commons.core.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import loxia.support.json.JSONArray;
import loxia.support.json.JSONException;
import loxia.support.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.configure.PropertiesUtil;

/**
 * 用来 快速的获得 json, 交由开发人员自己控制 log 输出级别
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 23, 2012 3:33:42 PM
 */
public final class JsonFormatUtil{

	private static final Logger	log						= LoggerFactory.getLogger(JsonFormatUtil.class);

	/**
	 * 默认的
	 */
	public static final String	defaultPropFilterStr	= "**,-class";

	// ********************************************Map*************************************************************************
	/**
	 * 获得 map (4,4) 格式化的 log 字符串 用于自定义级别的输出<br>
	 * 默认 String propFilterStr = "**,-class";
	 * 
	 * @param map
	 * @return
	 */
	public static String format(Map<String, ? extends Object> map){
		return format(map, defaultPropFilterStr);
	}

	/**
	 * 获得 map (4,4) 格式化的 log 字符串 用于自定义级别的输出
	 * 
	 * @param map
	 *            map
	 * @param propFilterStr
	 *            propFilterStr 如果 NullOrEmpty 设置为""
	 * @return jsonObject.toString(4, 4) 之后的 json 字符串 <br>
	 *         如果 JSONException 返回null
	 * @throws JSONException
	 */
	public static String format(Map<String, ? extends Object> map,String propFilterStr){

		if (Validator.isNotNullOrEmpty(map)){

			if (Validator.isNullOrEmpty(propFilterStr)){
				propFilterStr = defaultPropFilterStr;
			}
			// 这个key format 有问题
			// map.remove(Config.FMT_LOCALIZATION_CONTEXT + ".request");
			// javax.servlet.jsp.jstl.fmt.localizationContext.request
			// java.lang.IllegalAccessException:
			// Class loxia.support.json.JSONObject can not access a member of class
			// org.springframework.web.servlet.support.JstlUtils$SpringLocalizationContext
			// with modifiers "public"
			// model 已经 exposeModelAsRequestAttributes

			JSONObject jsonObject = new JSONObject(map, propFilterStr);// , propFilterStr
			try{
				// indent 最顶层的缩进数
				// indentFactor 每一级的缩进数
				String logString = jsonObject.toString(4, 4);
				return logString;
			}catch (JSONException e){
				e.printStackTrace();
			}
		}

		return null;
	}

	// **************************************Collection*******************************************************************************

	/**
	 * 获得List 格式化的 log 字符串 用于自定义级别的输出<br>
	 * 默认 String propFilterStr = "**,-class";
	 * 
	 * @param list
	 *            collection
	 * @return jsonArray.toString(4, 4) 之后的 json 字符串 <br>
	 *         如果 JSONException 返回null
	 */
	public static String format(Collection<? extends Object> collection){
		return format(collection, defaultPropFilterStr);
	}

	/**
	 * 获得List 格式化的 log 字符串 用于自定义级别的输出
	 * 
	 * @param collection
	 * @return jsonArray.toString(4, 4) 之后的 json 字符串 <br>
	 *         如果 JSONException 返回null
	 */
	public static String format(Collection<? extends Object> collection,String propFilterStr){
		if (Validator.isNotNullOrEmpty(collection)){

			log.debug("list size is :{}", collection.size());
			JSONArray jsonArray = new JSONArray(collection, propFilterStr);

			try{
				// indent 最顶层的缩进数
				// indentFactor 每一级的缩进数
				String logString = jsonArray.toString(4, 4);
				return logString;
			}catch (JSONException e){
				e.printStackTrace();
			}
		}
		return null;
	}

	// ********************************************Object********************************************************************
	/**
	 * 输出JsonObject (4,4) 格式化<br>
	 * 默认 String propFilterStr = "**,-class";
	 * 
	 * @param object
	 *            如果 object object instanceof Properties 则 转成map 调用 map format<br>
	 *            如果 object object instanceof Object[] 则format(Arrays.asList(objects))
	 */
	public static String format(Object object){
		if (Validator.isNotNullOrEmpty(object)){

			if (object instanceof Properties){
				Properties properties = (Properties) object;
				return format(PropertiesUtil.toMap(properties));
			}
			// 数组
			if (object instanceof Object[]){
				Object[] objects = (Object[]) object;
				return format(Arrays.asList(objects));
			}

			String propFilterStr = defaultPropFilterStr;
			if (object instanceof Number){
				propFilterStr = "*";
			}
			return format(object, propFilterStr);
		}
		return null;
	}

	public static String format(Object object,String propFilterStr){
		if (Validator.isNotNullOrEmpty(object)){

			if (Validator.isNullOrEmpty(propFilterStr)){
				propFilterStr = "";
			}
			JSONObject jsonObject = new JSONObject(object, propFilterStr);
			try{
				// indent 最顶层的缩进数
				// indentFactor 每一级的缩进数
				String logString = jsonObject.toString(4, 4);
				return logString;
			}catch (JSONException e){
				e.printStackTrace();
			}
		}
		return null;
	}
}
