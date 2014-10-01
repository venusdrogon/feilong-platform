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
 * 中文过滤器 处理乱码.
 *
 * @author 金鑫 时间:2009-2-13上午09:50:10
 */
public class ChineseFilter implements Filter{

	/** The code. */
	private String	code	= CharsetType.GB2312;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
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
		if (null != httpServletRequest.getHeader("X-Requested-With")
						&& httpServletRequest.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")){
			request.setCharacterEncoding(CharsetType.UTF8);
		}else{
			request.setCharacterEncoding(code);
		}
		response.setCharacterEncoding(code);
		filterChain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy(){}
}
