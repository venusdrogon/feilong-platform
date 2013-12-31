/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.servlet.http.RequestUtil;

/**
 * Servlet 2.3/2.4 Filter that allows one to specify a character encoding for requests. This is useful because current browsers typically do not set a character
 * encoding even if specified in the HTML page or form.
 * <p>
 * This filter can either apply its encoding if the request does not already specify an encoding, or enforce this filter's encoding in any case
 * ("forceEncoding"="true"). In the latter case, the encoding will also be applied as default response encoding on Servlet 2.4+ containers (although this will
 * usually be overridden by a full content type set in the view).
 * 
 * @author Juergen Hoeller
 * @since 15.03.2004
 * @see #setEncoding
 * @see #setForceEncoding
 * @see javax.servlet.http.HttpServletRequest#setCharacterEncoding
 * @see javax.servlet.http.HttpServletResponse#setCharacterEncoding
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-27 上午10:56:40
 */
public class CharacterEncodingFilter extends OncePerRequestFilter{

	private String	encoding;

	private boolean	forceEncoding	= false;

	//***************************************************************
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException,IOException{
		if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)){
			/**
			 * jQuery在使用ajax的时候会在header中加入X-Requested-With，值为：XMLHttpRequest，
			 * 
			 * <pre>
			 * 
			 * 此时一律使用post 方式提交,并且传递参数通通放到第二个位置,不要和连接拼接起来
			 * </pre>
			 */
			if (RequestUtil.isAjaxRequest(request)){
				request.setCharacterEncoding(CharsetType.UTF8);
			}else{
				request.setCharacterEncoding(this.encoding);
			}
			if (this.forceEncoding){
				response.setCharacterEncoding(this.encoding);
			}
		}
		filterChain.doFilter(request, response);
	}

	//***************************************************************
	/**
	 * Set the encoding to use for requests. This encoding will be passed into a {@link javax.servlet.http.HttpServletRequest#setCharacterEncoding} call.
	 * <p>
	 * Whether this encoding will override existing request encodings (and whether it will be applied as default response encoding as well) depends on the
	 * {@link #setForceEncoding "forceEncoding"} flag.
	 */
	public void setEncoding(String encoding){
		this.encoding = encoding;
	}

	/**
	 * Set whether the configured {@link #setEncoding encoding} of this filter is supposed to override existing request and response encodings.
	 * <p>
	 * Default is "false", i.e. do not modify the encoding if {@link javax.servlet.http.HttpServletRequest#getCharacterEncoding()} returns a non-null value.
	 * Switch this to "true" to enforce the specified encoding in any case, applying it as default response encoding as well.
	 * <p>
	 * Note that the response encoding will only be set on Servlet 2.4+ containers, since Servlet 2.3 did not provide a facility for setting a default response
	 * encoding.
	 */
	public void setForceEncoding(boolean forceEncoding){
		this.forceEncoding = forceEncoding;
	}
}
