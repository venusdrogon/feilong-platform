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
package com.feilong.servlet.http;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 处理参数相关
 * 
 * @author 金鑫 2010-4-15 下午04:01:29
 */
public final class ParamUtil{

	private ParamUtil(){}

	private static String	defaultCharsetType	= CharsetType.UTF8;

	// *********************************获取值**************************************************
	/**
	 * 将参数值转换成int类型
	 * 
	 * @param request
	 *            请求
	 * @param paramName
	 *            参数名称
	 * @return 将参数值转换成int类型
	 */
	public static Integer getParameterToInteger(HttpServletRequest request,String paramName){
		String value = getParameter(request, paramName);
		return ObjectUtil.toInteger(value);
	}

	/**
	 * 将参数值转换成BigDecimal类型
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
	 * 获得request中的请求参数值
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
	 * 参数值去除井号,一般用于sendDirect 跳转中带有#标签,参数值取不准确的问题
	 * 
	 * @param request
	 * @param paramName
	 * @return 参数值去除井号,一般用于sendDirect 跳转中带有#标签,参数值取不准确的问题
	 */
	public static String getParameterWithoutSharp(HttpServletRequest request,String paramName){
		String returnValue = getParameter(request, paramName);
		if (Validator.isNotNullOrEmpty(returnValue)){
			if (StringUtil.isContain(returnValue, URIUtil.fragment)){
				returnValue = StringUtil.substring(returnValue, null, URIUtil.fragment);
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
			Map<String, String> map = URIUtil.parseQueryToMap(queryString);
			return map.get(paramName);
		}
		return returnValue;
	}

	/**
	 * FormatHTML取到参数值,通常处理文本域里的换行问题
	 * 
	 * @param request
	 * @param paramName
	 *            参数名称
	 * @return FormatHTML取到参数值,通常处理文本域里的换行问题
	 */
	@Deprecated
	public static String getParameterWithFormatHTML(HttpServletRequest request,String paramName){
		String returnValue = getParameter(request, paramName);
		returnValue = returnValue.replace("\r\n", "<br/>");// 换行
		return returnValue;
	}

	/**
	 * 取到参数值,没有返回null,有去除空格返回
	 * 
	 * @param request
	 *            当前请求
	 * @param paramName
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
	 * 添加参数 加入含有该参数会替换掉
	 * 
	 * @param url
	 * @param paramName
	 *            添加的参数名称
	 * @param parameValue
	 *            添加的参数值
	 * @return 添加参数 加入含有该参数会替换掉
	 */
	public static String addParameter(String url,String paramName,Object parameValue,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return addParameter(uri, paramName, parameValue, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉
	 * 
	 * @param uri
	 *            URI 统一资源标识符 (URI),<br>
	 *            如果带有? 和参数,会先被截取,最后再拼接,<br>
	 *            如果不带?,则自动 增加?
	 * @param nameAndValueMap
	 *            nameAndValueMap param name 和value 的键值对
	 * @return 添加参数 加入含有该参数会替换掉
	 * @exception 如果FeiLongValidator.isNull
	 *                (nameAndValueMap) 则IllegalArgumentException
	 */
	public static String addParameter(String url,Map<String, ? extends Object> nameAndValueMap,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return addParameter(uri, nameAndValueMap, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(paramName, parameValue);
		return addParameter(uri, map, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉
	 * 
	 * @param uri
	 *            URI 统一资源标识符 (URI),<br>
	 *            如果带有? 和参数,会先被截取,最后再拼接,<br>
	 *            如果不带?,则自动 增加?
	 * @param nameAndValueMap
	 *            nameAndValueMap param name 和value 的键值对
	 * @param charsetType
	 *            编码
	 * @return 添加参数 加入含有该参数会替换掉
	 * @exception 如果FeiLongValidator.isNull
	 *                (nameAndValueMap) 则IllegalArgumentException
	 */
	public static String addParameter(URI uri,Map<String, ? extends Object> nameAndValueMap,String charsetType){
		if (null == uri){
			throw new IllegalArgumentException("uri can not be null!");
		}
		if (Validator.isNullOrEmpty(nameAndValueMap)){
			throw new IllegalArgumentException("nameAndValueMap can not be null!");
		}
		// ***********************************************************************
		String url = uri.toString();
		String before = URIUtil.getBefore(url);
		// ***********************************************************************
		// getQuery() 返回此 URI 的已解码的查询组成部分。
		// getRawQuery() 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
		String query = uri.getRawQuery();
		// ***********************************************************************
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		// 传入的url不带参数的情况
		if (Validator.isNullOrEmpty(query)){
			// nothing to do
		}else{
			Map<String, String> originalMap = URIUtil.parseQueryToMap(query);
			map.putAll(originalMap);
		}
		map.putAll(nameAndValueMap);
		// **************************************************************
		return URIUtil.getEncodedUrl(before, map, charsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉
	 * 
	 * @param uri
	 *            URI 统一资源标识符 (URI),<br>
	 *            如果带有? 和参数,会先被截取,最后再拼接,<br>
	 *            如果不带?,则自动 增加?
	 * @param nameAndValueMap
	 *            nameAndValueMap param name 和value 的键值对
	 * @return 添加参数 加入含有该参数会替换掉
	 * @exception 如果FeiLongValidator.isNull
	 *                (nameAndValueMap) 则IllegalArgumentException
	 */
	@Deprecated
	public static String addParameter(String url,Map<String, ? extends Object> nameAndValueMap){
		URI uri = URIUtil.create(url);
		return addParameter(uri, nameAndValueMap, defaultCharsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉<br>
	 * 默认使用CharsetType.UTF8解析参数
	 * 
	 * @param url
	 * @param paramName
	 *            添加的参数名称
	 * @param parameValue
	 *            添加的参数值
	 * @return 添加参数 加入含有该参数会替换掉
	 * @deprecated 请使用{@link #addParameter(String, String, Object, String)} 代替,<br>
	 *             分页组件 vm会调用 暂时还不能删除
	 */
	public static String addParameter(String url,String paramName,Object parameValue){
		return addParameter(url, paramName, parameValue, defaultCharsetType);
	}

	/**
	 * 添加参数 加入含有该参数会替换掉 <br>
	 * 默认使用CharsetType.UTF8解析参数
	 * 
	 * @param uri
	 * @param paramName
	 *            添加的参数名称
	 * @param parameValue
	 *            添加的参数值
	 * @return
	 * @deprecated 分页组件 vm会调用 暂时还不能删除
	 */
	public static String addParameter(URI uri,String paramName,Object parameValue){
		return addParameter(uri, paramName, parameValue, defaultCharsetType);
	}

	// ********************************removeParameter*********************************************************************

	/**
	 * 删除参数
	 * 
	 * @param url
	 * @param paramName
	 * @param charsetType
	 *            编码
	 * @return
	 */
	public static String removeParameter(String url,String paramName,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return removeParameter(uri, paramName, charsetType);
	}

	/**
	 * 删除参数
	 * 
	 * @param uri
	 * @param paramName
	 * @param charsetType
	 *            编码
	 * @return
	 */
	private static String removeParameter(URI uri,String paramName,String charsetType){
		List<String> paramNameList = null;
		if (Validator.isNotNullOrEmpty(paramName)){
			paramNameList = new ArrayList<String>();
			paramNameList.add(paramName);
		}
		return removeParameter(uri, paramNameList, charsetType);
	}

	/**
	 * 删除参数
	 * 
	 * @param url
	 * @param paramNameList
	 * @param charsetType
	 *            编码
	 * @return
	 */
	public static String removeParameter(String url,List<String> paramNameList,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return removeParameter(uri, paramNameList, charsetType);
	};

	/**
	 * 删除参数
	 * 
	 * @param uri
	 * @param paramNameList
	 * @param charsetType
	 *            编码
	 * @return
	 */
	public static String removeParameter(URI uri,List<String> paramNameList,String charsetType){
		if (null == uri){
			return "";
		}
		String url = uri.toString();
		// 如果 paramNameList 是null 原样返回
		if (Validator.isNullOrEmpty(paramNameList)){
			return url;
		}
		// ***********************************************************************
		String before = URIUtil.getBefore(url);
		// ***********************************************************************
		// 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
		String query = uri.getRawQuery();
		// ***********************************************************************
		// 传入的url不带参数的情况
		if (Validator.isNullOrEmpty(query)){
			// 不带参数原样返回
			return url;
		}else{
			Map<String, String> map = URIUtil.parseQueryToMap(query);
			for (String paramName : paramNameList){
				map.remove(paramName);
			}
			return URIUtil.getEncodedUrl(before, map, charsetType);
		}
	}

	/**
	 * 删除参数
	 * 
	 * @param url
	 * @param paramName
	 * @return
	 * @deprecated use removeParameter(url, paramName, charsetType)
	 */
	public static String removeParameter(String url,String paramName){
		return removeParameter(url, paramName, defaultCharsetType);
	}

	// **************************************retentionParams********************************************************

	/**
	 * url里面仅保留 指定的参数
	 * 
	 * @param url
	 * @param paramNameList
	 * @param charsetType
	 *            编码
	 * @return
	 */
	public static String retentionParams(String url,List<String> paramNameList,String charsetType){
		URI uri = URIUtil.create(url, charsetType);
		return retentionParams(uri, paramNameList, charsetType);
	}

	/**
	 * url里面仅保留 指定的参数
	 * 
	 * @param uri
	 * @param paramNameList
	 * @param charsetType
	 *            编码
	 * @return
	 */
	public static String retentionParams(URI uri,List<String> paramNameList,String charsetType){
		if (null == uri){
			return "";
		}else{
			String url = uri.toString();
			// 如果 paramNameList 是null 原样返回
			if (Validator.isNullOrEmpty(paramNameList)){
				return url;
			}
			String before = URIUtil.getBefore(url);
			// ***********************************************************************
			// 返回此 URI 的原始查询组成部分。 URI 的查询组成部分（如果定义了）只包含合法的 URI 字符。
			String query = uri.getRawQuery();
			// ***********************************************************************
			// 传入的url不带参数的情况
			if (Validator.isNullOrEmpty(query)){
				// 不带参数原样返回
				return url;
			}else{
				Map<String, String> map = new LinkedHashMap<String, String>();
				Map<String, String> originalMap = URIUtil.parseQueryToMap(query);
				for (String paramName : paramNameList){
					map.put(paramName, originalMap.get(paramName));
				}
				return URIUtil.getEncodedUrl(before, map, charsetType);
			}
		}
	}

	/**
	 * url里面仅保留 指定的参数<br>
	 * 默认使用utf-8
	 * 
	 * @param url
	 * @param paramNameList
	 * @return
	 * @deprecated 请使用带参数 {@link #retentionParams(String, List, String)} 代替
	 */
	public static String retentionParams(String url,List<String> paramNameList){
		return retentionParams(url, paramNameList, defaultCharsetType);
	}

}
