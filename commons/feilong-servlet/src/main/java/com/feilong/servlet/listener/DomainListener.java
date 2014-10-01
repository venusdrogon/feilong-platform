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

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.web.entity.domain.DomainAttributes;
import com.feilong.web.entity.domain.DomainType;
import com.feilong.web.entity.domain.DomainUtil;

/**
 * 初始化配置 监听器.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Aug 19, 2013 10:28:05 AM
 */
public class DomainListener implements ServletContextListener{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(DomainListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent){
		ServletContext servletContext = servletContextEvent.getServletContext();
		initDomain(servletContext);
	}

	/**
	 * 初始化二级域名.
	 * 
	 * @param servletContext
	 *            the servlet context
	 */
	private void initDomain(ServletContext servletContext){
		// ********************************domain****************************************
		String domain_css = DomainUtil.getDomain(servletContext, DomainType.CSS);
		servletContext.setAttribute(DomainAttributes.ATTRIBUTE_DOMAIN_CSS, domain_css);
		// ******************
		String domain_js = DomainUtil.getDomain(servletContext, DomainType.JS);
		servletContext.setAttribute(DomainAttributes.ATTRIBUTE_DOMAIN_JS, domain_js);
		// ******************
		String domain_image = DomainUtil.getDomain(servletContext, DomainType.IMAGE);
		servletContext.setAttribute(DomainAttributes.ATTRIBUTE_DOMAIN_IMAGE, domain_image);
		// ******************
		String domain_resource = DomainUtil.getDomain(servletContext, DomainType.RESOURCE);
		servletContext.setAttribute(DomainAttributes.ATTRIBUTE_DOMAIN_RESOURCE, domain_resource);
		// ******************
		String domain_store = DomainUtil.getDomain(servletContext, DomainType.STORE);
		servletContext.setAttribute(DomainAttributes.ATTRIBUTE_DOMAIN_STORE, domain_store);
		// *******************************************************************

		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("\n[domain_js]:", domain_js);
		map.put("\n[domain_css]:", domain_css);
		map.put("\n[domain_image]:", domain_image);
		map.put("\n[domain_resource]:", domain_resource);
		map.put("\n[domain_store]:", domain_store);

		servletContext.log(JsonUtil.format(map));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce){
		// currently do nothing
	}
}
