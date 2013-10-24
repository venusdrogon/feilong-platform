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
package com.feilong.application.taobao.entity;

import java.io.Serializable;

/**
 *基于OAuth2.0的登录验证授权,获取授权码Code
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-10-17 下午02:40:09
 */
public class TaoBaoOAuthLoginForCodeEntity implements Serializable{

	private static final long	serialVersionUID	= -8336561371508312277L;

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/authorize
	 */
	private String				requestUrl;

	/**
	 * Y 此处为web应用，此值固定为code
	 */
	private String				response_type;

	/**
	 * Y 即创建应用时的Appkey
	 */
	private String				client_id;

	/**
	 * Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配
	 */
	private String				redirect_uri;

	/**
	 * N 该参数由应用定义，用户授权后，授权服务器会原封不动将此参数返回。<br>
	 * 注: 应用可通过可选参数state来记录用户当前所处的页面位置信息，方便用户登录授权后，页面回调到用户之前所处的位置。
	 */
	private String				state;

	/**
	 * Y 此处为web应用，此值固定为code
	 * 
	 * @return the response_type
	 */
	public String getResponse_type(){
		return response_type;
	}

	/**
	 * Y 此处为web应用，此值固定为code
	 * 
	 * @param responseType
	 *            the response_type to set
	 */
	public void setResponse_type(String responseType){
		response_type = responseType;
	}

	/**
	 * Y 即创建应用时的Appkey
	 * 
	 * @return the client_id
	 */
	public String getClient_id(){
		return client_id;
	}

	/**
	 * Y 即创建应用时的Appkey
	 * 
	 * @param clientId
	 *            the client_id to set
	 */
	public void setClient_id(String clientId){
		client_id = clientId;
	}

	/**
	 * Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配
	 * 
	 * @return the redirect_uri
	 */
	public String getRedirect_uri(){
		return redirect_uri;
	}

	/**
	 * Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配
	 * 
	 * @param redirectUri
	 *            the redirect_uri to set
	 */
	public void setRedirect_uri(String redirectUri){
		redirect_uri = redirectUri;
	}

	/**
	 * N 该参数由应用定义，用户授权后，授权服务器会原封不动将此参数返回。<br>
	 * 注: 应用可通过可选参数state来记录用户当前所处的页面位置信息，方便用户登录授权后，页面回调到用户之前所处的位置。
	 * 
	 * @return the state
	 */
	public String getState(){
		return state;
	}

	/**
	 * N 该参数由应用定义，用户授权后，授权服务器会原封不动将此参数返回。<br>
	 * 注: 应用可通过可选参数state来记录用户当前所处的页面位置信息，方便用户登录授权后，页面回调到用户之前所处的位置。
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(String state){
		this.state = state;
	}

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/authorize
	 * 
	 * @return the requestUrl
	 */
	public String getRequestUrl(){
		return requestUrl;
	}

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/authorize
	 * 
	 * @param requestUrl
	 *            the requestUrl to set
	 */
	public void setRequestUrl(String requestUrl){
		this.requestUrl = requestUrl;
	}
}
