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
package com.feilong.spring.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

/**
 * UrlPathHelper.
 * 
 * @author <a href="mailtovenusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-30 下午91957
 */
public class UrlPathHelperUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(UrlPathHelperUtil.class);

	/**
	 * 显示 属性 一般 debug 使用 看看
	 * 
	 * <pre>
	 * 示例 _search-filter-search.jsp为 被 jspinclude 的 页面
	 * 
	 * http//www.feilong.com8888/converse/s/c-m-c-s-k-s-o.htm?keyword=%E9%9E%8B
	 * urlPathHelper.getContextPath(request)/converse
	 * urlPathHelper.getOriginatingContextPath(request)/converse
	 * urlPathHelper.getOriginatingQueryString(request)keyword=%E9%9E%8B
	 * urlPathHelper.getOriginatingRequestUri(request)/converse/s/c-m-c-s-k-s-o.htm
	 * urlPathHelper.getPathWithinApplication(request)/product/_search-filter-search.jsp
	 * urlPathHelper.getPathWithinServletMapping(request)
	 * urlPathHelper.getRequestUri(request)/converse/product/_search-filter-search.jsp
	 * urlPathHelper.getServletPath(request)/product/_search-filter-search.jsp
	 * </pre>
	 * 
	 * <pre>
	 * http//www.feilong.com8888/s/c-m-c-s-k-s-o.htm?keyword=%E9%9E%8B
	 * urlPathHelper.getContextPath(request)
	 * urlPathHelper.getOriginatingContextPath(request)
	 * urlPathHelper.getOriginatingQueryString(request)keyword=%E9%9E%8B
	 * urlPathHelper.getOriginatingRequestUri(request)/s/c-m-c-s-k-s-o.htm
	 * urlPathHelper.getPathWithinApplication(request)/product/_search-filter-search.jsp
	 * urlPathHelper.getPathWithinServletMapping(request)
	 * urlPathHelper.getRequestUri(request)/product/_search-filter-search.jsp
	 * urlPathHelper.getServletPath(request)/product/_search-filter-search.jsp
	 * </pre>
	 * 
	 * @param request
	 *            the request
	 * @return the url path helper map for log
	 */
	public static Map<String, Object> getUrlPathHelperMapForLog(HttpServletRequest request){

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		UrlPathHelper urlPathHelper = new UrlPathHelper();

		map.put("urlPathHelper.getContextPath(request)", urlPathHelper.getContextPath(request));
		map.put("urlPathHelper.getLookupPathForRequest(request)", urlPathHelper.getLookupPathForRequest(request));
		map.put("urlPathHelper.getOriginatingContextPath(request)", urlPathHelper.getOriginatingContextPath(request));
		map.put("urlPathHelper.getOriginatingQueryString(request)", urlPathHelper.getOriginatingQueryString(request));
		map.put("urlPathHelper.getOriginatingRequestUri(request)", urlPathHelper.getOriginatingRequestUri(request));

		// 3.1.0
		map.put("urlPathHelper.getOriginatingServletPath(request)", urlPathHelper.getOriginatingServletPath(request));

		map.put("urlPathHelper.getPathWithinApplication(request)", urlPathHelper.getPathWithinApplication(request));
		map.put("urlPathHelper.getPathWithinServletMapping(request)", urlPathHelper.getPathWithinServletMapping(request));
		map.put("urlPathHelper.getRequestUri(request)", urlPathHelper.getRequestUri(request));
		map.put("urlPathHelper.getServletPath(request)", urlPathHelper.getServletPath(request));

		return map;
	}
}
