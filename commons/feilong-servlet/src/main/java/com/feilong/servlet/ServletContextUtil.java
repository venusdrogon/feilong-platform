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
package com.feilong.servlet;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.ExceptionConstants;
import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * ServletContextUtil
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-31 下午12:53:01
 */
public final class ServletContextUtil{

	private static final Logger	log	= LoggerFactory.getLogger(ServletContextUtil.class);

	/**
	 * servletContext.log servletContext相关信息,一般 启动时 调用
	 * 
	 * @param servletContext
	 */
	public static void showProperty(ServletContext servletContext){
		// 返回servlet运行的servlet 容器的版本和名称。
		servletContext.log("[servletContext.getServerInfo()]:" + servletContext.getServerInfo());
		servletContext.log("[servletContext.getContextPath()]:" + servletContext.getContextPath());
		servletContext.log("[servletContext.getServletContextName()]:" + servletContext.getServletContextName());
		// 返回这个servlet容器支持的Java Servlet API的主要版本。所有符合2.5版本的实现，必须有这个方法返回的整数2。
		// 返回这个servlet容器支持的Servlet API的次要版本。所有符合2.5版本的实现，必须有这个方法返回整数5。
		String servletVersion = StringUtil.format(
				"[servlet version]:%s.%s",
				servletContext.getMajorVersion(),
				servletContext.getMinorVersion());
		servletContext.log(servletVersion);
	}

	/**
	 * 遍历显示servletContext的attribute,将 name /attributeValue 存入到map
	 * 
	 * @param session
	 */
	public static Map<String, Object> getAttributeMap(ServletContext servletContext){
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> attributeNames = servletContext.getAttributeNames();
		while (attributeNames.hasMoreElements()){
			String name = attributeNames.nextElement();
			Object attributeValue = servletContext.getAttribute(name);
			map.put(name, attributeValue);
		}
		return map;
	}

	/**
	 * 遍历显示servletContext的 InitParameterNames,将 name /attributeValue 存入到map
	 * 
	 * @param servletContext
	 */
	public static Map<String, String> getInitParameterMap(ServletContext servletContext){
		Map<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
		if (Validator.isNotNullOrEmpty(initParameterNames)){
			while (initParameterNames.hasMoreElements()){
				String name = initParameterNames.nextElement();
				String value = servletContext.getInitParameter(name);
				map.put(name, value);
			}
		}
		return map;
	}

	/**
	 * 读取servletContext.getRealPath("/")下面,文件内容
	 * 
	 * @param servletContext
	 *            servletContext上下文地址
	 * @param directoryName
	 *            文件夹路径
	 *            <ul>
	 *            <li>如果是根目录,则directoryName传null</li>
	 *            <li>前面没有/ 后面有/ 如:res/html/email/</li>
	 *            </ul>
	 * @param fileName
	 *            文件名称 如:register.html
	 * @return 读取文件内容
	 * @deprecated
	 */
	public static String getFileContent(ServletContext servletContext,String directoryName,String fileName){
		String filePathString = servletContext.getRealPath("/");
		if (Validator.isNullOrEmpty(fileName)){
			throw new IllegalArgumentException(ExceptionConstants.exception_fileName_null);
		}
		if (Validator.isNullOrEmpty(directoryName)){
			filePathString = filePathString + fileName;
		}else{
			filePathString = filePathString + directoryName + fileName;
		}
		// ServletContext servletContext = request.getSession().getServletContext();
		return IOReaderUtil.getFileContent(filePathString);
	}
}
