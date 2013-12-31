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

package com.feilong.servlet.listener;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ClassLoaderUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.servlet.ServletContextUtil;

/**
 * 专门给esapi 设置的监听器<br>
 * 使用步骤:
 * <ul>
 * <li>1.只需要将 esapi 目录copy 到项目中classpath下即可，</li>
 * <li>2.web.xml 配置 <code>
 *  <listener>
 * 		<listener-class>com.feilong.servlet.listener.ESAPIContextListener</listener-class>
 * 	</listener>
 * </code></li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 9, 2012 5:16:58 PM
 */
public class ESAPIContextListener implements ServletContextListener{

	private static final Logger	log	= LoggerFactory.getLogger(ESAPIContextListener.class);

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent){
		ServletContext servletContext = servletContextEvent.getServletContext();

		Map<String, String> initParameterMap = ServletContextUtil.getInitParameterMap(servletContext);

		if (log.isInfoEnabled()){
			log.info("initParameterMap:{}", JsonFormatUtil.format(initParameterMap));
		}

		try{
			// 取到classes/esapi 绝对地址,
			// 此处不能直接使用ClassLoaderUtil.getResource("esapi") 做org.owasp.esapi.resources 参数值
			// 否则 后面拼接的路径 会将 file:/ 纯粹当 文件路径一部分拼接
			File esapiDirectory = new File(ClassLoaderUtil.getResource("esapi").toURI());
			String customDirectory = esapiDirectory.getAbsolutePath();

			File file = new File(customDirectory, "ESAPI.properties");

			servletContext.log("ESAPI.properties getAbsolutePath():" + file.getAbsolutePath());
			servletContext.log("customDirectory != null && file.canRead():" + (customDirectory != null && file.canRead()) + "");

			// 设置值 以便esapi使用
			System.setProperty("org.owasp.esapi.resources", customDirectory);
			servletContext.log("set setProperty org.owasp.esapi.resources:" + customDirectory);
		}catch (URISyntaxException e){
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce){
		// TODO Auto-generated method stub
	}
}
