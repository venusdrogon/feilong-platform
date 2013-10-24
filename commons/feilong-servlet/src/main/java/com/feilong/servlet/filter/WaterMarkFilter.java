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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.awt.WaterMark;

/**
 * 水印过滤器
 * 
 * @author 金鑫 2010-1-6 下午01:07:33
 */
public class WaterMarkFilter extends HttpServlet implements Filter{

	private static final long	serialVersionUID	= 1L;

	private FilterConfig		filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException{
		this.filterConfig = filterConfig;
	}

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
	@Override
	public void destroy(){}
}