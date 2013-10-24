/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * * Filter base class that guarantees to be just executed once per request, on any servlet container. It provides a {@link #doFilterInternal} method with
 * HttpServletRequest and HttpServletResponse arguments.
 * <p>
 * The {@link #getAlreadyFilteredAttributeName} method determines how to identify that a request is already filtered. The default implementation is based on the
 * configured name of the concrete filter instance.
 * 
 * @author Juergen Hoeller
 * @since 06.12.2003
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-27 上午11:16:20
 */
public abstract class OncePerRequestFilter extends GenericFilterBean{

	/**
	 * Suffix that gets appended to the filter name for the "already filtered" request attribute.
	 * 
	 * @see #getAlreadyFilteredAttributeName
	 */
	public static final String	ALREADY_FILTERED_SUFFIX	= ".FILTERED";

	/**
	 * This <code>doFilter</code> implementation stores a request attribute for "already filtered", proceeding without filtering again if the attribute is
	 * already there.
	 * 
	 * @see #getAlreadyFilteredAttributeName
	 * @see #shouldNotFilter
	 * @see #doFilterInternal
	 */
	@Override
	public final void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain) throws ServletException,IOException{
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)){
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String alreadyFilteredAttributeName = getAlreadyFilteredAttributeName();
		if (request.getAttribute(alreadyFilteredAttributeName) != null || shouldNotFilter(httpRequest)){
			// Proceed without invoking this filter...
			filterChain.doFilter(request, response);
		}else{
			// Do invoke this filter...
			request.setAttribute(alreadyFilteredAttributeName, Boolean.TRUE);
			try{
				doFilterInternal(httpRequest, httpResponse, filterChain);
			}finally{
				// Remove the "already filtered" request attribute for this request.
				request.removeAttribute(alreadyFilteredAttributeName);
			}
		}
	}

	/**
	 * Return the name of the request attribute that identifies that a request is already filtered.
	 * <p>
	 * Default implementation takes the configured name of the concrete filter instance and appends ".FILTERED". If the filter is not fully initialized, it
	 * falls back to its class name.
	 * 
	 * @see #getFilterName
	 * @see #ALREADY_FILTERED_SUFFIX
	 */
	protected String getAlreadyFilteredAttributeName(){
		String name = getFilterName();
		if (name == null){
			name = getClass().getName();
		}
		return name + ALREADY_FILTERED_SUFFIX;
	}

	/**
	 * Same contract as for <code>doFilter</code>, but guaranteed to be just invoked once per request. Provides HttpServletRequest and HttpServletResponse
	 * arguments instead of the default ServletRequest and ServletResponse ones.
	 */
	protected abstract void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException,
			IOException;

	/**
	 * Can be overridden in subclasses for custom filtering control, returning <code>true</code> to avoid filtering of the given request.
	 * <p>
	 * The default implementation always returns <code>false</code>.
	 * 
	 * @param request
	 *            current HTTP request
	 * @return whether the given request should <i>not</i> be filtered
	 * @throws ServletException
	 *             in case of errors
	 */
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
		return false;
	}
}
