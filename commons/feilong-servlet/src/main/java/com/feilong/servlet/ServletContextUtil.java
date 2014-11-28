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
package com.feilong.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.BeanUtil;
import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.commons.core.lang.ObjectUtil;
import com.feilong.commons.core.util.Validator;

/**
 * ServletContextUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-31 下午12:53:01
 */
public final class ServletContextUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(ServletContextUtil.class);

	/**
	 * servletContext.log servletContext相关信息,一般 启动时 调用
	 *
	 * @param servletContext
	 *            the servlet context
	 * @return the map< string, object>
	 */
	public static Map<String, Object> getServletContextMapForLog(ServletContext servletContext){

		Map<String, Object> map = new HashMap<String, Object>();
		// 返回servlet运行的servlet 容器的版本和名称。
		map.put("servletContext.getServerInfo()", servletContext.getServerInfo());

		// 返回这个servlet容器支持的Java Servlet API的主要版本。所有符合2.5版本的实现，必须有这个方法返回的整数2。
		// 返回这个servlet容器支持的Servlet API的次要版本。所有符合2.5版本的实现，必须有这个方法返回整数5。
		map.put("servlet version:", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());

		map.put("servletContext.getContextPath()", servletContext.getContextPath());

		map.put("servletContext.getServletContextName()", servletContext.getServletContextName());

		return map;
	}

	/**
	 * 遍历显示servletContext的attribute,将 name /attributeValue 存入到map.
	 * 
	 * @param servletContext
	 *            the servlet context
	 * @return the attribute map
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

	public static Map<String, String> getAttributeStringMapForLog(ServletContext servletContext){
		Map<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> attributeNames = servletContext.getAttributeNames();
		while (attributeNames.hasMoreElements()){
			String name = attributeNames.nextElement();
			Object attributeValue = servletContext.getAttribute(name);

			map.put(name, "" + attributeValue);
		}
		return map;
	}

	/**
	 * 遍历显示servletContext的 InitParameterNames,将 name /attributeValue 存入到map.
	 * 
	 * @param servletContext
	 *            the servlet context
	 * @return the inits the parameter map
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
	 * @throws IOException
	 * @deprecated
	 */
	public static String getFileContent(ServletContext servletContext,String directoryName,String fileName) throws IOException{
		String filePathString = servletContext.getRealPath("/");
		if (Validator.isNullOrEmpty(fileName)){
			throw new IllegalArgumentException("fileName can't be null/empty");
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
