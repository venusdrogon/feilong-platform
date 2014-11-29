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
package com.feilong.spring.web.util;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UrlPathHelper;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.RequestUtil;

/**
 * UriTemplate,此类是 单值 expend.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-22 上午11:24:41
 */
public class UriTemplateUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(UriTemplateUtil.class);

	/**
	 * 获得当前uri template 变量值.
	 * 
	 * @param request
	 *            the request
	 * @param pathVarName
	 *            the path var name
	 * @return the uri template variable value
	 */
	public static String getUriTemplateVariableValue(HttpServletRequest request,String pathVarName){
		Map<String, String> uriTemplateVariables = getUriTemplateVariables(request);
		if (uriTemplateVariables == null || !uriTemplateVariables.containsKey(pathVarName)){
			throw new IllegalStateException("Could not find @PathVariable [" + pathVarName + "] in @RequestMapping");
		}
		String value = uriTemplateVariables.get(pathVarName);
		return value;
	}

	/**
	 * 判断模板请求里面 是否有指定的 变量名称.
	 * 
	 * @param request
	 *            the request
	 * @param pathVarName
	 *            指定的变量名称
	 * @return true, if successful
	 */
	public static boolean hasPathVarName(HttpServletRequest request,String pathVarName){
		Map<String, String> uriTemplateVariables = getUriTemplateVariables(request);
		if (uriTemplateVariables == null || !uriTemplateVariables.containsKey(pathVarName)){
			return false;
		}
		return true;
	}

	/**
	 * Gets the uri template variables.
	 * 
	 * @param request
	 *            the request
	 * @return request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)
	 */
	public static Map<String, String> getUriTemplateVariables(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		Map<String, String> uriTemplateVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		return uriTemplateVariables;
	}

	/**
	 * 获得最佳的HandlerMapping映射内的匹配模式。.
	 * 
	 * @param request
	 *            the request
	 * @return the best matching pattern
	 */
	public static String getBestMatchingPattern(HttpServletRequest request){
		String bestMatchingPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return bestMatchingPattern;
	}

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
	 * @return 获得一个新的url,参数部分会被原样返回<br>
	 *         urlPathHelper.getOriginatingContextPath(request) + expandUrl + (Validator.isNotNull(queryString) ? "?" + queryString : "");
	 */
	public static String expandBestMatchingPattern(HttpServletRequest request,String variableName,String value){
		String requestPath = RequestUtil.getOriginatingServletPath(request);
		String matchingPatternPath = getBestMatchingPattern(request);// 这种方法可能不太好 可能被覆盖
		String expandUrl = expandWithVariable(requestPath, matchingPatternPath, variableName, value);
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
	 * 扩充模板值
	 * 
	 * <pre>
	 * String requestPath = &quot;/s/c-m-c-s-k-s-o.htm&quot; 
	 * String matchingPatternPath = &quot;/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm&quot;
	 * String variableName="color";
	 * String value="100";
	 * expandWithVariable(requestPath,matchingPatternPath,variableName,value);
	 * 
	 * return /s/c-m-c<span style="color:red">100</span>-s-k-s-o.htm?keyword=鞋;
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
	 * @return 获得一个新的url<br>
	 *         参数部分 需要自己添加
	 */
	public static String expandWithVariable(String requestPath,String matchingPatternPath,String variableName,String value){
		Map<String, String> map = extractUriTemplateVariables(requestPath, matchingPatternPath);
		map.put(variableName, value);
		return expand(matchingPatternPath, map);
	}

	/**
	 * Given a pattern and a full path, extract the URI template variables.<br>
	 * URI template variables are expressed through curly brackets ('{' and '}'). <br>
	 * For example: For pattern "/hotels/{hotel}" and path "/hotels/1", this method will return a map containing "hotel"->"1".
	 * 
	 * @param requestPath
	 *            the request path
	 * @param matchingPatternPath
	 *            the matching pattern path
	 * @return the map
	 */
	public static Map<String, String> extractUriTemplateVariables(String requestPath,String matchingPatternPath){
		log.debug("the param requestPath:{}", requestPath);
		PathMatcher matcher = new AntPathMatcher();
		Map<String, String> map = matcher.extractUriTemplateVariables(matchingPatternPath, requestPath);
		return map;
	}

	/**
	 * 扩充模板值
	 * 
	 * <pre>
	 * String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
	 * String variableName = "color";
	 * String value = "100";
	 * expandWithVariable(matchingPatternPath, variableName, value);
	 * 
	 * return /s/c-m-c<span style="color:red">100</span>-s-k-s-o.htm
	 * </pre>
	 * 
	 * @param matchingPatternPath
	 *            the matching pattern path
	 * @param variableName
	 *            the variable name
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String expandWithVariable(String matchingPatternPath,String variableName,String value){
		Map<String, String> map = new HashMap<String, String>();
		map.put(variableName, value);
		return expand(matchingPatternPath, map);
	}

	/**
	 * 清除 基于 variableNames 变量名称的 值
	 * 
	 * <pre>
	 * 
	 * String requestPath = &quot;/s/c500-m60-cred-s-k-s100-o6.htm&quot;;
	 * String matchingPatternPath = &quot;/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm&quot;;
	 * String[] variableNames = { &quot;color&quot;, &quot;style&quot; };
	 * log.info(UriTemplateUtil.clearVariablesValue(requestPath, matchingPatternPath, variableNames));
	 * 
	 * return /s/c500-m60-c-s-k-s-o6.htm
	 * </pre>
	 * 
	 * @param requestPath
	 *            请求路径
	 * @param matchingPatternPath
	 *            匹配模式
	 * @param variableNames
	 *            变量数组
	 * @return the string
	 */
	public static String clearVariablesValue(String requestPath,String matchingPatternPath,String[] variableNames){
		Map<String, String> map = extractUriTemplateVariables(requestPath, matchingPatternPath);
		if (Validator.isNotNullOrEmpty(variableNames)){
			// 将这些变量的值 设为""
			for (String variableName : variableNames){
				map.put(variableName, "");
			}
		}
		return expand(matchingPatternPath, map);
	}

	/**
	 * 仅仅保留这些参数的值,和 clearVariablesValue相反
	 * 
	 * <pre>
	 * 
	 * String requestPath = &quot;/s/c500-m60-cred-s-k-s100-o6.htm&quot;;
	 * String matchingPatternPath = &quot;/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm&quot;;
	 * String[] variableNames = { &quot;color&quot;, &quot;style&quot; };
	 * log.info(UriTemplateUtil.clearVariablesValue(requestPath, matchingPatternPath, variableNames));
	 * 
	 * return /s/c-m-cred-s-k-s100-o.htm
	 * </pre>
	 * 
	 * @param requestPath
	 *            请求路径
	 * @param matchingPatternPath
	 *            匹配模式
	 * @param variableNames
	 *            变量数组
	 * @return the string
	 * @see #clearVariablesValue(String, String, String[])
	 */
	public static String retainVariablesValue(String requestPath,String matchingPatternPath,String[] variableNames){

		Map<String, String> map = new HashMap<String, String>();

		if (Validator.isNotNullOrEmpty(variableNames)){
			Map<String, String> _map = extractUriTemplateVariables(requestPath, matchingPatternPath);
			for (String variableName : variableNames){
				map.put(variableName, _map.get(variableName));
			}
		}
		return expand(matchingPatternPath, map);
	}

	/**
	 * 扩充模板值
	 * 
	 * <pre>
	 *  String uriTemplatePath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
	 *  Map<String, String> map = new HashMap<String, String>();
	 *  map.put("color", "100");
	 *  map.put("size", "L");
	 *   
	 * return /s/c-m-c<span style="color:red">100</span>-s<span style="color:red">L</span>-k-s-o.htm
	 * </pre>
	 * 
	 * @param uriTemplatePath
	 *            模板
	 * @param map
	 *            变量-值 map <br>
	 *            如果 传入的map 中的key 不在模板中,自动忽略<br>
	 *            即只处理 模板中有的key
	 * @return the string
	 */
	public static String expand(String uriTemplatePath,Map<String, String> map){
		// 所有的变量
		List<String> variableNames = getVariableNames(uriTemplatePath);
		Map<String, String> _map = new LinkedHashMap<String, String>();
		// 基于变量 生成对应的 值空map
		for (String variableName : variableNames){
			_map.put(variableName, null);
		}
		if (Validator.isNotNullOrEmpty(map)){
			_map.putAll(map);// map如果是null 会报错
		}
		UriTemplate uriTemplate = new UriTemplate(uriTemplatePath);
		URI uri = uriTemplate.expand(_map);
		return uri.toString();
	}

	/**
	 * 变量名称 Return the names of the variables in the template, in order.
	 * 
	 * @param uriTemplatePath
	 *            the uri template path
	 * @return the variable names
	 */
	public static List<String> getVariableNames(String uriTemplatePath){
		UriTemplate uriTemplate = new UriTemplate(uriTemplatePath);
		List<String> variableNames = uriTemplate.getVariableNames();
		return variableNames;
	}
}
