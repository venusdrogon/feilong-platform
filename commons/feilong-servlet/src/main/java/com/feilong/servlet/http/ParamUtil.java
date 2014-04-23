/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.servlet.http;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.net.URIConstants;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 处理参数相关.
 * 
 * @author 金鑫 2010-4-15 下午04:01:29
 */
public final class ParamUtil{

	/**
	 * Instantiates a new param util.
	 */
	private ParamUtil(){}

	/**
	 * 生成待签名的字符串 <br>
	 * 对数组里的每一个值从 a 到 z 的顺序排序，若遇到相同首字母，则看第二个字母， 以此类推。 排序完成之后，再把所有数组值以“&”字符连接起来 <br>
	 * 没有值的参数无需传递，也无需包含到待签名数据中.
	 * 
	 * @param params
	 *            用于凭借签名的参数
	 * @return the to be signed string
	 */
	public static String getToBeSignedString(Map<String, String> params){
		List<String> keys = new ArrayList<String>(params.keySet());

		// key 排序
		Collections.sort(keys);

		StringBuilder builder = new StringBuilder();
		int size = keys.size();
		for (int i = 0; i < size; ++i){
			String key = keys.get(i);
			String value = params.get(key);

			builder.append(key + "=" + value);
			// 最后一个不要拼接 &
			if (i != size - 1){
				builder.append("&");
			}
		}
		return builder.toString();
	}

	// *********************************获取值**************************************************
	/**
	 * 将参数值转换成int类型.
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 将参数值转换成int类型
	 * @exception IllegalArgumentException
	 *                如果param值 不能转成integer 会抛出 IllegalArgumentException异常
	 */
	public static Integer getParameterToInteger(HttpServletRequest request,String paramName){
		String value = getParameter(request, paramName);
		return ObjectUtil.toInteger(value);
	}

	/**
	 * 将参数值转换成BigDecimal类型.
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 将参数值转换成BigDecimal类型
	 */
	public static BigDecimal getParameterToBigDecimal(HttpServletRequest request,String paramName){
		String value = getParameter(request, paramName);
		return ObjectUtil.toBigDecimal(value);
	}

	/**
	 * 获得request中的请求参数值.
	 * 
	 * @param request
	 *            当前请求
	 * @param paramName
	 *            参数名称
	 * @return 获得request中的请求参数值
	 */
	public static String getParameter(HttpServletRequest request,String paramName){
		return request.getParameter(paramName);
	}

	/**
	 * 参数值去除井号,一般用于sendDirect 跳转中带有#标签,参数值取不准确的问题.
	 * 
	 * @param request
	 *            the request
	 * @param paramName
	 *            the param name
	 * @return 参数值去除井号,一般用于sendDirect 跳转中带有#标签,参数值取不准确的问题
	 */
	public static String getParameterWithoutSharp(HttpServletRequest request,String paramName){
		String returnValue = getParameter(request, paramName);
		if (Validator.isNotNullOrEmpty(returnValue)){
			if (StringUtil.isContain(returnValue, URIConstants.FRAGMENT)){
				returnValue = StringUtil.substring(returnValue, null, URIConstants.FRAGMENT);
			}
		}
		return returnValue;
	}

	/**
	 * 原样获得参数值(request.getParameter()函数时，会自动进行一次URI的解码过程，调用时内置的解码过程会导致乱码出现)
	 * 
	 * <pre>
	 * url参数是什么,取到的就是什么,不经过处理
	 * </pre>
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 原样获得参数值
	 * @deprecated
	 */
	public static String getParameterAsItIsDecode(HttpServletRequest request,String paramName){
		String returnValue = null;
		String queryString = request.getQueryString();
		if (Validator.isNotNullOrEmpty(queryString)){
			Map<String, String[]> map = URIUtil.parseQueryToArrayMap(queryString, null);
			return map.get(paramName)[0];
		}
		return returnValue;
	}

	/**
	 * 取到参数值,没有返回null,有去除空格返回.
	 * 
	 * @param request
	 *            当前请求
	 * @param paramName
	 *            the param name
	 * @return 取到参数值,没有返回null,有去除空格返回
	 */
	public static String getParameterWithTrim(HttpServletRequest request,String paramName){
		String returnValue = getParameter(request, paramName);
		if (Validator.isNotNullOrEmpty(returnValue)){
			returnValue = returnValue.trim();
		}
		return returnValue;
	}

	// ************************************addParameter******************************************************

	/**
	 * 添加参数 加入含有该参数会替换掉.
	 * 
	 * @param url
	 *            the url
	 * @param paramName
	 *            添加的参数名称
	 * @param parameValue
	 *            添加的参数值
	 * @param charsetType
	 *            the charset type
	 * @return 添加参数 加入含有该参数会替换掉
	 */
	public static String addParameter(String url,String paramName,Object parameValue,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return addParameter(uri, paramName, parameValue, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉.
	 * 
	 * @param url
	 *            the url
	 * @param nameAndValuesMap
	 *            nameAndValueMap param name 和value 的键值对
	 * @param charsetType
	 *            the charset type
	 * @return 添加参数 加入含有该参数会替换掉
	 */
	public static String addParameterArrayMap(String url,Map<String, String[]> nameAndValuesMap,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return addParameterArrayMap(uri, nameAndValuesMap, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉.
	 * 
	 * @param url
	 *            the url
	 * @param nameAndValueMap
	 *            nameAndValueMap param name 和value 的键值对
	 * @param charsetType
	 *            the charset type
	 * @return the string
	 */
	public static String addParameterValueMap(String url,Map<String, String> nameAndValueMap,String charsetType){
		Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();

		if (Validator.isNotNullOrEmpty(nameAndValueMap)){
			for (Map.Entry<String, String> entry : nameAndValueMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				keyAndArrayMap.put(key, new String[] { value });
			}
		}
		return addParameterArrayMap(url, keyAndArrayMap, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉.
	 * 
	 * @param uri
	 *            URI 统一资源标识符 (URI),<br>
	 *            如果带有? 和参数,会先被截取,最后再拼接,<br>
	 *            如果不带?,则自动 增加?
	 * @param paramName
	 *            添加的参数名称
	 * @param parameValue
	 *            添加的参数值
	 * @param charsetType
	 *            编码
	 * @return 添加参数 加入含有该参数会替换掉
	 */
	public static String addParameter(URI uri,String paramName,Object parameValue,String charsetType){
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put(paramName, new String[] { parameValue.toString() });
		return addParameterArrayMap(uri, map, charsetType);
	}

	/**
	 * 添加参数 <br>
	 * 假如含有该参数会替换掉，比如原来是a=1&a=2,现在使用a,[3,4]调用这个方法， 会返回a=3&a=4.
	 * 
	 * @param uri
	 *            URI 统一资源标识符 (URI),<br>
	 *            如果带有? 和参数,会先被截取,最后再拼接,<br>
	 *            如果不带?,则自动 增加?
	 * @param nameAndValueMap
	 *            nameAndValueMap 类似于 request.getParameterMap
	 * @param charsetType
	 *            编码
	 * @return 添加参数 加入含有该参数会替换掉
	 */
	public static String addParameterArrayMap(URI uri,Map<String, String[]> nameAndValueMap,String charsetType){
		if (null == uri){
			throw new IllegalArgumentException("uri can not be null!");
		}
		if (Validator.isNullOrEmpty(nameAndValueMap)){
			throw new IllegalArgumentException("nameAndValueMap can not be null!");
		}
		// ***********************************************************************
		String url = uri.toString();
		String before = URIUtil.getBeforePath(url);
		// ***********************************************************************
		// getQuery() 返回此 URI 的已解码的查询组成部分。
		// getRawQuery() 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
		String query = uri.getRawQuery();
		// ***********************************************************************
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		// 传入的url不带参数的情况
		if (Validator.isNullOrEmpty(query)){
			// nothing to do
		}else{
			Map<String, String[]> originalMap = URIUtil.parseQueryToArrayMap(query, null);
			map.putAll(originalMap);
		}
		map.putAll(nameAndValueMap);
		// **************************************************************
		return URIUtil.getEncodedUrlByArrayMap(before, map, charsetType);
	}

	// ********************************removeParameter*********************************************************************

	/**
	 * 删除参数.
	 * 
	 * @param url
	 *            the url
	 * @param paramName
	 *            the param name
	 * @param charsetType
	 *            编码
	 * @return the string
	 */
	public static String removeParameter(String url,String paramName,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return removeParameter(uri, paramName, charsetType);
	}

	/**
	 * 删除参数.
	 * 
	 * @param uri
	 *            the uri
	 * @param paramName
	 *            the param name
	 * @param charsetType
	 *            编码
	 * @return the string
	 */
	private static String removeParameter(URI uri,String paramName,String charsetType){
		List<String> paramNameList = null;
		if (Validator.isNotNullOrEmpty(paramName)){
			paramNameList = new ArrayList<String>();
			paramNameList.add(paramName);
		}
		return removeParameterList(uri, paramNameList, charsetType);
	}

	/**
	 * 删除参数.
	 * 
	 * @param url
	 *            the url
	 * @param paramNameList
	 *            the param name list
	 * @param charsetType
	 *            编码
	 * @return the string
	 */
	public static String removeParameterList(String url,List<String> paramNameList,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return removeParameterList(uri, paramNameList, charsetType);
	};

	/**
	 * 删除参数.
	 * 
	 * @param uri
	 *            the uri
	 * @param paramNameList
	 *            the param name list
	 * @param charsetType
	 *            编码
	 * @return the string
	 */
	public static String removeParameterList(URI uri,List<String> paramNameList,String charsetType){
		if (null == uri){
			return "";
		}
		String url = uri.toString();
		// 如果 paramNameList 是null 原样返回
		if (Validator.isNullOrEmpty(paramNameList)){
			return url;
		}
		// ***********************************************************************
		String before = URIUtil.getBeforePath(url);
		// ***********************************************************************
		// 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
		String query = uri.getRawQuery();
		// ***********************************************************************
		// 传入的url不带参数的情况
		if (Validator.isNullOrEmpty(query)){
			// 不带参数原样返回
			return url;
		}else{
			Map<String, String[]> map = URIUtil.parseQueryToArrayMap(query, null);
			for (String paramName : paramNameList){
				map.remove(paramName);
			}
			return URIUtil.getEncodedUrlByArrayMap(before, map, charsetType);
		}
	}

	// **************************************retentionParams********************************************************

	/**
	 * url里面仅保留 指定的参数.
	 * 
	 * @param url
	 *            the url
	 * @param paramNameList
	 *            the param name list
	 * @param charsetType
	 *            编码
	 * @return the string
	 */
	public static String retentionParamList(String url,List<String> paramNameList,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return retentionParamList(uri, paramNameList, charsetType);
	}

	/**
	 * url里面仅保留 指定的参数.
	 * 
	 * @param uri
	 *            the uri
	 * @param paramNameList
	 *            the param name list
	 * @param charsetType
	 *            编码
	 * @return the string
	 */
	public static String retentionParamList(URI uri,List<String> paramNameList,String charsetType){
		if (null == uri){
			return "";
		}else{
			String url = uri.toString();
			// 如果 paramNameList 是null 原样返回
			if (Validator.isNullOrEmpty(paramNameList)){
				return url;
			}
			String before = URIUtil.getBeforePath(url);
			// ***********************************************************************
			// 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
			String query = uri.getRawQuery();
			// ***********************************************************************
			// 传入的url不带参数的情况
			if (Validator.isNullOrEmpty(query)){
				// 不带参数原样返回
				return url;
			}else{
				Map<String, String[]> map = new LinkedHashMap<String, String[]>();

				Map<String, String[]> originalMap = URIUtil.parseQueryToArrayMap(query, null);

				for (String paramName : paramNameList){
					map.put(paramName, originalMap.get(paramName));
				}
				return URIUtil.getEncodedUrlByArrayMap(before, map, charsetType);
			}
		}
	}
}
