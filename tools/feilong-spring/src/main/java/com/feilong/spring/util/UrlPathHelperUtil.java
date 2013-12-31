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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

/**
 * UrlPathHelper
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-30 下午9:19:57
 */
public class UrlPathHelperUtil{

	private static final Logger	log	= LoggerFactory.getLogger(UrlPathHelperUtil.class);

	/**
	 * 显示 属性 一般 debug 使用 看看
	 * 
	 * <pre>
	 * 示例 _search-filter-search.jsp为 被 jsp:include 的 页面
	 * 
	 * http://www.feilong.com:8888/converse/s/c-m-c-s-k-s-o.htm?keyword=%E9%9E%8B
	 * urlPathHelper.getContextPath(request):/converse
	 * urlPathHelper.getOriginatingContextPath(request):/converse
	 * urlPathHelper.getOriginatingQueryString(request):keyword=%E9%9E%8B
	 * urlPathHelper.getOriginatingRequestUri(request):/converse/s/c-m-c-s-k-s-o.htm
	 * urlPathHelper.getPathWithinApplication(request):/product/_search-filter-search.jsp
	 * urlPathHelper.getPathWithinServletMapping(request):
	 * urlPathHelper.getRequestUri(request):/converse/product/_search-filter-search.jsp
	 * urlPathHelper.getServletPath(request):/product/_search-filter-search.jsp
	 * </pre>
	 * 
	 * <pre>
	 * http://www.feilong.com:8888/s/c-m-c-s-k-s-o.htm?keyword=%E9%9E%8B
	 * urlPathHelper.getContextPath(request):
	 * urlPathHelper.getOriginatingContextPath(request):
	 * urlPathHelper.getOriginatingQueryString(request):keyword=%E9%9E%8B
	 * urlPathHelper.getOriginatingRequestUri(request):/s/c-m-c-s-k-s-o.htm
	 * urlPathHelper.getPathWithinApplication(request):/product/_search-filter-search.jsp
	 * urlPathHelper.getPathWithinServletMapping(request):
	 * urlPathHelper.getRequestUri(request):/product/_search-filter-search.jsp
	 * urlPathHelper.getServletPath(request):/product/_search-filter-search.jsp
	 * </pre>
	 * 
	 * @param request
	 */
	public static void showProperties(HttpServletRequest request){
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		if (log.isDebugEnabled()){
			log.debug("urlPathHelper.getContextPath(request):{}", urlPathHelper.getContextPath(request));
			log.debug("urlPathHelper.getOriginatingContextPath(request):{}", urlPathHelper.getOriginatingContextPath(request));
			log.debug("urlPathHelper.getOriginatingQueryString(request):{}", urlPathHelper.getOriginatingQueryString(request));
			log.debug("urlPathHelper.getOriginatingRequestUri(request):{}", urlPathHelper.getOriginatingRequestUri(request));
			// 3.1.0
			// log.debug("urlPathHelper.getOriginatingServletPath(request):{}", urlPathHelper.getOriginatingServletPath(request));
			log.debug("urlPathHelper.getPathWithinApplication(request):{}", urlPathHelper.getPathWithinApplication(request));
			log.debug("urlPathHelper.getPathWithinServletMapping(request):{}", urlPathHelper.getPathWithinServletMapping(request));
			log.debug("urlPathHelper.getRequestUri(request):{}", urlPathHelper.getRequestUri(request));
			log.debug("urlPathHelper.getServletPath(request):{}", urlPathHelper.getServletPath(request));
		}
	}
}
