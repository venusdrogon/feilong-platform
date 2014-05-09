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
package com.feilong.spring.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.util.CollectionUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.json.JsonUtil;

/**
 * MultiUriTemplateUtil,某些商城筛选条件可以是多值,比如 既是 儿童 又是 男子<br>
 * 此时url 应该是混合式的 而不是覆盖.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-22 上午11:24:41
 */
public class MultiUriTemplateUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MultiUriTemplateUtil.class);

	/**
	 * 自动寻找matchingPatternPath 扩充模板值<br>
	 * urlPathHelper.getOriginatingContextPath(request) + expandUrl + (Validator.isNotNull(queryString) ? "?" + queryString : "");
	 * 
	 * @param request
	 *            the request
	 * @param variableName
	 *            模板变量
	 * @param value
	 *            the value
	 * @param valueSeparator
	 *            多值,分隔符
	 * @return 获得一个新的url,参数部分会被原样返回<br>
	 *         urlPathHelper.getOriginatingContextPath(request) + expandUrl + (Validator.isNotNull(queryString) ? "?" + queryString : "");
	 */
	public static String expandBestMatchingPatternMulti(HttpServletRequest request,String variableName,String value,String valueSeparator){
		String requestPath = RequestUtil.getOriginatingServletPath(request);
		String matchingPatternPath = UriTemplateUtil.getBestMatchingPattern(request);// 这种方法可能不太好 可能被覆盖
		String expandUrl = expandWithMultiVariable(requestPath, matchingPatternPath, variableName, value, valueSeparator);
		String queryString = request.getQueryString();
		Map<String, Object> map = UrlPathHelperUtil.getUrlPathHelperMapForLog(request);
		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(map));
		}

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		return urlPathHelper.getOriginatingContextPath(request) + expandUrl
				+ (Validator.isNullOrEmpty(queryString) ? "?" + queryString : "");
	}

	/**
	 * 多值,扩充模板值
	 * 
	 * <pre>
	 * String requestPath = "/s/c-m-c-s-k-s100-o.htm";
	 * String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
	 * String variableName = "style";
	 * String value = "200";
	 *  
	 *  return /s/c-m-c-s-k-s<span style="color:red">100,200</span>-o.htm
	 *  
	 * 重复的value 不会被反复添加
	 * String requestPath = "/s/c-m-c-s-k-s<span style="color:#0000FF">100,200</span>-o.htm";
	 * String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
	 * String variableName = "style";
	 * String value = "200";
	 *  
	 *  return /s/c-m-c-s-k-s<span style="color:red">100,200</span>-o.htm
	 * </pre>
	 * 
	 * @param requestPath
	 *            the request path
	 * @param matchingPatternPath
	 *            the matching pattern path
	 * @param variableName
	 *            模板变量名称
	 * @param value
	 *            指定值
	 * @param valueSeparator
	 *            多值,分隔符,尽量不要使用 | * .做分隔符,使用的是正则表达式分隔的
	 * @return 获得一个新的url<br>
	 *         参数部分 需要自己添加
	 */
	public static String expandWithMultiVariable(
			String requestPath,
			String matchingPatternPath,
			String variableName,
			String value,
			String valueSeparator){
		Map<String, String> map = UriTemplateUtil.extractUriTemplateVariables(requestPath, matchingPatternPath);
		return expandWithMultiVariable(matchingPatternPath, map, variableName, value, valueSeparator);
	}

	/**
	 * 多值,扩充模板值
	 * 
	 * <pre>
	 * 
	 * String matchingPatternPath = &quot;/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm&quot;;
	 * Map&lt;String, String&gt; map = new HashMap&lt;String, String&gt;();
	 * map.put(&quot;categoryCode&quot;, &quot;2541&quot;);
	 * map.put(&quot;style&quot;, &quot;100&quot;);
	 * 
	 * String variableName = &quot;style&quot;;
	 * String value = &quot;900&quot;;
	 * String valueSeparator = &quot;,&quot;;
	 * log.info(MultiUriTemplateUtil.expandWithMultiVariable(matchingPatternPath, map, variableName, value, valueSeparator));
	 * 
	 * 
	 * return "/s/c2541-m-c-s-k-s100,900-o.htm"
	 * </pre>
	 * 
	 * @param matchingPatternPath
	 *            the matching pattern path
	 * @param map
	 *            the map
	 * @param variableName
	 *            the variable name
	 * @param value
	 *            the value
	 * @param valueSeparator
	 *            the value separator
	 * @return the string
	 */
	public static String expandWithMultiVariable(
			String matchingPatternPath,
			Map<String, String> map,
			String variableName,
			String value,
			String valueSeparator){
		if (Validator.isNullOrEmpty(map)){
			map = new HashMap<String, String>();
		}
		// 原值
		String oldValue = map.get(variableName);
		if (Validator.isNullOrEmpty(oldValue)){
			map.put(variableName, value);
		}else{
			String[] oldValues = oldValue.split(valueSeparator);
			// Arrays.asList(oldValues)如果 直接使用 Arrays.asList(oldValues) 下面一行 会抛出java.lang.UnsupportedOperationException
			List<String> list = new ArrayList<String>(Arrays.asList(oldValues));

			// 保证重复的value 不会被反复添加
			if (list.contains(value)){
				log.debug("list contains value:{}", value);
			}else{
				list.add(value);
			}
			JoinStringEntity joinStringEntity = new JoinStringEntity(valueSeparator);
			map.put(variableName, CollectionUtil.toString(list, joinStringEntity));
		}
		return UriTemplateUtil.expand(matchingPatternPath, map);
	}

	/**
	 * 将一个值 从一个变量多值中以除掉
	 * 
	 * <pre>
	 * String requestPath = &quot;/s/c-m-c-s-k-s<span style="color:red">500,100,200,9000</span>-o.htm&quot;;
	 * String matchingPatternPath = &quot;/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm&quot;;
	 * String variableName = &quot;style&quot;;
	 * String value = &quot;200&quot;;
	 * String valueSeparator = &quot;,&quot;;
	 * log.info(MultiUriTemplateUtil.removeMultiVariableValue(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	 * 
	 * return /s/c-m-c-s-k-s<span style="color:red">500,100,9000</span>-o.htm
	 * 
	 * </pre>
	 * 
	 * @param requestPath
	 *            the request path
	 * @param matchingPatternPath
	 *            the matching pattern path
	 * @param variableName
	 *            模板变量名称
	 * @param value
	 *            指定值,将会被从这个变量多值中移除
	 * @param valueSeparator
	 *            多值,分隔符,尽量不要使用 | * .做分隔符,使用的是正则表达式分隔的
	 * @return <ul>
	 *         <li>如果模板variableName,对应的值是NullOrEmpty,则原样返回requestPath</li>
	 *         <li>如果模板variableName,对应的值,经过valueSeparator分隔成数组中不包含value,则原样返回requestPath</li>
	 *         <li>移掉数组中的值重新拼接成url</li>
	 *         </ul>
	 */
	public static String removeMultiVariableValue(
			String requestPath,
			String matchingPatternPath,
			String variableName,
			String value,
			String valueSeparator){
		Map<String, String> map = UriTemplateUtil.extractUriTemplateVariables(requestPath, matchingPatternPath);

		String oldValue = map.get(variableName);
		// 如果没有值
		if (Validator.isNullOrEmpty(oldValue)){
			Object[] objects = { requestPath, matchingPatternPath, variableName };
			log.debug("the requestPath:{},matchingPatternPath:{},variableName:{},value is null or empty~~~", objects);
		}else{
			String[] oldValues = oldValue.split(valueSeparator);

			// Arrays.asList(oldValues)如果 直接使用 Arrays.asList(oldValues) 下面一行 会抛出java.lang.UnsupportedOperationException
			List<String> list = new ArrayList<String>(Arrays.asList(oldValues));

			// 如果值里面有 就移除
			if (list.contains(value)){
				list.remove(value);

				JoinStringEntity joinStringEntity = new JoinStringEntity(valueSeparator);
				map.put(variableName, CollectionUtil.toString(list, joinStringEntity));

				return UriTemplateUtil.expand(matchingPatternPath, map);
			}else{
				Object[] objects = { requestPath, matchingPatternPath, variableName, oldValue, value };
				log.debug("the requestPath:{},matchingPatternPath:{},variableName:{},oldValue:{},not contains({})~~~", objects);
			}
		}
		// 原样输出
		return requestPath;
	}
}
