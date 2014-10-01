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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 通用过滤器 Simple base implementation of {@link javax.servlet.Filter} which treats its config parameters (<code>init-param</code> entries
 * within the <code>filter</code> tag in <code>web.xml</code>) as bean properties.
 * <p>
 * A handy superclass for any type of filter. Type conversion of config parameters is automatic, with the corresponding setter method
 * getting invoked with the converted value. It is also possible for subclasses to specify required properties. Parameters without matching
 * bean property setter will simply be ignored.
 * <p>
 * This filter leaves actual filtering to subclasses, which have to implement the {@link javax.servlet.Filter#doFilter} method.
 * <p>
 * This generic filter base class has no dependency on the Spring {@link org.springframework.context.ApplicationContext} concept. Filters
 * usually don't load their own context but rather access service beans from the Spring root application context, accessible via the
 * filter's {@link #getServletContext()
 * ServletContext} (see {@link org.springframework.web.context.support.WebApplicationContextUtils}).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-27 上午11:17:47
 */
public abstract class GenericFilterBean implements Filter{

	/* The FilterConfig of this filter */
	/** The filter config. */
	private FilterConfig	filterConfig;

	//*******************************************************************************
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy(){}

	//******************************通用方法**********************************************
	/**
	 * Make the name of this filter available to subclasses. Analogous to GenericServlet's <code>getServletName()</code>.
	 * <p>
	 * Takes the FilterConfig's filter name by default. If initialized as bean in a Spring application context, it falls back to the bean
	 * name as defined in the bean factory.
	 * 
	 * @return the filter name, or <code>null</code> if none available
	 * @see javax.servlet.GenericServlet#getServletName()
	 * @see javax.servlet.FilterConfig#getFilterName()
	 * @see #setBeanName
	 */
	protected final String getFilterName(){
		return (this.filterConfig != null ? this.filterConfig.getFilterName() : "");
	}

	/**
	 * Make the ServletContext of this filter available to subclasses. Analogous to GenericServlet's <code>getServletContext()</code>.
	 * <p>
	 * Takes the FilterConfig's ServletContext by default. If initialized as bean in a Spring application context, it falls back to the
	 * ServletContext that the bean factory runs in.
	 * 
	 * @return the ServletContext instance, or <code>null</code> if none available
	 * @see javax.servlet.GenericServlet#getServletContext()
	 * @see javax.servlet.FilterConfig#getServletContext()
	 * @see #setServletContext
	 */
	protected final ServletContext getServletContext(){
		return (this.filterConfig != null ? this.filterConfig.getServletContext() : null);
	}
}
