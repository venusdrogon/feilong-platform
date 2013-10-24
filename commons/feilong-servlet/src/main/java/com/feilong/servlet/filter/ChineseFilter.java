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
package com.feilong.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * 中文过滤器 处理乱码
 * 
 * @author 金鑫 时间:2009-2-13上午09:50:10
 */
public class ChineseFilter implements Filter{

	private String	code	= CharsetType.GB2312;

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain) throws IOException,ServletException{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		/**
		 * jQuery在使用ajax的时候会在header中加入X-Requested-With，值为：XMLHttpRequest，
		 * 
		 * <pre>
		 * 
		 * 此时一律使用post 方式提交,并且传递参数通通放到第二个位置,不要和连接拼接起来
		 * </pre>
		 */
		if (null != httpServletRequest.getHeader("X-Requested-With") && httpServletRequest.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")){
			request.setCharacterEncoding(CharsetType.UTF8);
		}else{
			request.setCharacterEncoding(code);
		}
		response.setCharacterEncoding(code);
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException{}

	public void destroy(){}
}
