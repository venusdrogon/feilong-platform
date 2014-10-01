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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.awt.WaterMark;

/**
 * 水印过滤器.
 *
 * @author 金鑫 2010-1-6 下午01:07:33
 */
public class WaterMarkFilter extends HttpServlet implements Filter{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The filter config. */
	private FilterConfig		filterConfig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException{
		this.filterConfig = filterConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain) throws IOException,ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		// 获取请求中的图片路径
		String filePath = req.getServletPath();// 这是相对路径(并且包括了上下文路径)
		String fileRealPath = filterConfig.getServletContext().getRealPath(filePath);// 得到绝对路径
		// 水印图片
		String pressImage = filterConfig.getServletContext().getRealPath("res/feilong/images/face/xy2/17.gif");
		// 调用工具类加水印(由于是动态从流中加水印，因此不会修改服务器上原图片)
		// 写文字
		// WaterPress.pressText("ParamsFeiLong.projectChineseName", fileRealPath, "宋体", 1, Color.ORANGE.getRGB(), 15, 180, 15, response.getOutputStream());
		// 图片
		WaterMark.pressImage(fileRealPath, pressImage, 15, 180, response.getOutputStream());
		filterChain.doFilter(request, response);
	}

	// Clean up resources
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy(){}
}