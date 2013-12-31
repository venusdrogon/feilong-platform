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
package com.feilong.tools.om.nginx;

import java.io.Serializable;

/**
 * nginx log format.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 12:40:54 AM
 */
public final class NginxLogFormatCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 记录客户端的ip地址. */
	private String				remote_addr;

	/** 记录客户端用户名称,忽略属性"-". */
	private String				remote_user;

	/** 记录访问时间与时区. */
	private String				time_local;

	/** 记录请求的url与http协议. */
	private String				request;

	/** 记录请求状态；成功是200. */
	private String				status;

	/** 记录发送给客户端文件主体内容大小. */
	private String				body_bytes_sent;

	/** 用来记录从那个页面链接访问过来的. */
	private String				http_referer;

	/** 记录客户浏览器的相关信息. */
	private String				http_user_agent;

	/** The proxy_add_x_forwarded_for. */
	private String				proxy_add_x_forwarded_for;

	/** The upstream_addr. */
	private String				upstream_addr;

	/** The request_time. */
	private String				request_time;

	/**
	 * Gets the 记录客户端的ip地址.
	 * 
	 * @return the remote_addr
	 */
	public String getRemote_addr(){
		return remote_addr;
	}

	/**
	 * Sets the 记录客户端的ip地址.
	 * 
	 * @param remote_addr
	 *            the remote_addr to set
	 */
	public void setRemote_addr(String remote_addr){
		this.remote_addr = remote_addr;
	}

	/**
	 * Gets the 记录客户端用户名称,忽略属性"-".
	 * 
	 * @return the remote_user
	 */
	public String getRemote_user(){
		return remote_user;
	}

	/**
	 * Sets the 记录客户端用户名称,忽略属性"-".
	 * 
	 * @param remote_user
	 *            the remote_user to set
	 */
	public void setRemote_user(String remote_user){
		this.remote_user = remote_user;
	}

	/**
	 * Gets the 记录访问时间与时区.
	 * 
	 * @return the time_local
	 */
	public String getTime_local(){
		return time_local;
	}

	/**
	 * Sets the 记录访问时间与时区.
	 * 
	 * @param time_local
	 *            the time_local to set
	 */
	public void setTime_local(String time_local){
		this.time_local = time_local;
	}

	/**
	 * Gets the 记录请求的url与http协议.
	 * 
	 * @return the request
	 */
	public String getRequest(){
		return request;
	}

	/**
	 * Sets the 记录请求的url与http协议.
	 * 
	 * @param request
	 *            the request to set
	 */
	public void setRequest(String request){
		this.request = request;
	}

	/**
	 * Gets the 记录请求状态；成功是200.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Sets the 记录请求状态；成功是200.
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * Gets the 记录发送给客户端文件主体内容大小.
	 * 
	 * @return the body_bytes_sent
	 */
	public String getBody_bytes_sent(){
		return body_bytes_sent;
	}

	/**
	 * Sets the 记录发送给客户端文件主体内容大小.
	 * 
	 * @param body_bytes_sent
	 *            the body_bytes_sent to set
	 */
	public void setBody_bytes_sent(String body_bytes_sent){
		this.body_bytes_sent = body_bytes_sent;
	}

	/**
	 * Gets the 用来记录从那个页面链接访问过来的.
	 * 
	 * @return the http_referer
	 */
	public String getHttp_referer(){
		return http_referer;
	}

	/**
	 * Sets the 用来记录从那个页面链接访问过来的.
	 * 
	 * @param http_referer
	 *            the http_referer to set
	 */
	public void setHttp_referer(String http_referer){
		this.http_referer = http_referer;
	}

	/**
	 * Gets the 记录客户浏览器的相关信息.
	 * 
	 * @return the http_user_agent
	 */
	public String getHttp_user_agent(){
		return http_user_agent;
	}

	/**
	 * Sets the 记录客户浏览器的相关信息.
	 * 
	 * @param http_user_agent
	 *            the http_user_agent to set
	 */
	public void setHttp_user_agent(String http_user_agent){
		this.http_user_agent = http_user_agent;
	}

	/**
	 * Gets the proxy_add_x_forwarded_for.
	 * 
	 * @return the proxy_add_x_forwarded_for
	 */
	public String getProxy_add_x_forwarded_for(){
		return proxy_add_x_forwarded_for;
	}

	/**
	 * Sets the proxy_add_x_forwarded_for.
	 * 
	 * @param proxy_add_x_forwarded_for
	 *            the proxy_add_x_forwarded_for to set
	 */
	public void setProxy_add_x_forwarded_for(String proxy_add_x_forwarded_for){
		this.proxy_add_x_forwarded_for = proxy_add_x_forwarded_for;
	}

	/**
	 * Gets the upstream_addr.
	 * 
	 * @return the upstream_addr
	 */
	public String getUpstream_addr(){
		return upstream_addr;
	}

	/**
	 * Sets the upstream_addr.
	 * 
	 * @param upstream_addr
	 *            the upstream_addr to set
	 */
	public void setUpstream_addr(String upstream_addr){
		this.upstream_addr = upstream_addr;
	}

	/**
	 * Gets the request_time.
	 * 
	 * @return the request_time
	 */
	public String getRequest_time(){
		return request_time;
	}

	/**
	 * Sets the request_time.
	 * 
	 * @param request_time
	 *            the request_time to set
	 */
	public void setRequest_time(String request_time){
		this.request_time = request_time;
	}

}