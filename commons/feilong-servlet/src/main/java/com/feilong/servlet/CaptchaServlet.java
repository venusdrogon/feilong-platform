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
package com.feilong.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.Constants;
import com.feilong.commons.core.awt.ValidateCodeUtil;
import com.feilong.commons.core.enumeration.ImageType;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.servlet.http.ResponseUtil;

/**
 * 验证码
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 14, 2013 10:58:37 PM
 */
public class CaptchaServlet extends HttpServlet{

	private static final long	serialVersionUID	= -5495378311081182425L;

	private static final Logger	log					= LoggerFactory.getLogger(CaptchaServlet.class);

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#getLastModified(javax.servlet.http.HttpServletRequest)
	 */
	protected long getLastModified(HttpServletRequest request){
		return super.getLastModified(request);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

		// 设置页面不缓存
		ResponseUtil.setNoCache(response);
		// 保存生成的汉字字符串
		String validateCode = ValidateCodeUtil.generateValidateCode(4);

		String ip = RequestUtil.getClientIp(request);
		log.debug("session setAttribute SECURITY_CODE:[{}],ip:[{}]", validateCode, ip);

		// 将认证码存入session
		HttpSession session = request.getSession();
		session.setAttribute(Constants.Session.validateCode, validateCode);

		BufferedImage bufferedImage = ValidateCodeUtil.getBufferedImageAfterGraphics(validateCode, 88, 25);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		try{
			ImageIO.write(bufferedImage, ImageType.PNG, servletOutputStream);
			servletOutputStream.flush();
		}catch (Exception e){
			e.printStackTrace();
			servletOutputStream.close();
		}
	}

	private void writeImage(HttpServletResponse response,BufferedImage bufferedImage,PageContext pageContext){
		// 输出图象到页面
		try{
			ServletOutputStream servletOutputStream = response.getOutputStream();
			ImageIO.write(bufferedImage, ImageType.PNG, servletOutputStream);
			// ----------------------------------------------------------------------------------------
			servletOutputStream.flush();
			servletOutputStream.close();
			servletOutputStream = null;
			response.flushBuffer();
			// ----------------------------------------------------------------------------------------

			// 你是说下载吧?
			// 由于jsp container在处理完成请求后会调用releasePageContet方法释放所用的PageContext
			// object,并且同时调用getWriter方法,由于getWriter方法与在jsp页面中使用流相关的getOutputStream方法冲突,所以会造成这种异常,解决办法是:只需要在jsp页面的最后加上两条语句: out.clear();
			// out=pageContext.pushBody();即可(其中out,pageContext均为jsp内置对象!)

			// JspWriter jspWriter = response.getWriter();
			// jspWriter.clear();
			// jspWriter = pageContext.pushBody();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		service(request, response);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		service(request, response);
	}
}
