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
package com.feilong.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用设置的监听器<br>
 * 设置应用通用参数.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 9, 2012 5:16:58 PM
 */
//TODO 拆
public class ApplicationContextListener implements ServletContextListener{

	/** The Constant log. */
	private static final Logger	log							= LoggerFactory.getLogger(ApplicationContextListener.class);

	/** base 路径. */
	private static String		APPLICATIONATTRIBUTE_BASE	= "base";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce){
		ServletContext servletContext = sce.getServletContext();

		String contextPath = servletContext.getContextPath();
		servletContext.setAttribute(APPLICATIONATTRIBUTE_BASE, contextPath);
		servletContext.log("Set servletContext setAttribute: 'base' = [" + contextPath + "]");

		if (log.isInfoEnabled()){
			log.info("Set servletContext setAttribute: 'base' = [" + contextPath + "]");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce){}
}
