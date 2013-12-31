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

package com.feilong.spring.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.feilong.web.entity.Browser;

/**
 * 浏览器拦截器<br/>
 * 拦截每个请求 ,拿到UA,封装到 browser,你可以 通过此类来判断 访问方式是否是移动设备.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2013-3-25 15:18
 */
public class BrowserInterceptor extends HandlerInterceptorAdapter{

	/** The Constant log. */
	private static final Logger	log								= LoggerFactory.getLogger(BrowserInterceptor.class);

	/** 默认的 变量名称 <code>{@value}</code>. */
	public static final String	default_Browser_Request_Name	= "feilongBrowser";

	/** 请求参数,可以通过spring注入的方式 修改. */
	private String				attributeName					= default_Browser_Request_Name;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		Browser browser = new Browser(request);

		request.setAttribute(attributeName, browser);
		return true;
	}

	/**
	 * Sets the 请求参数.
	 * 
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName){
		this.attributeName = attributeName;
	}
}
