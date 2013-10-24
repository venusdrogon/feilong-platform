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
 *基于OAuth2.0的登录验证授权,用授权码Code换取Token
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-10-17 15:55:56
 */
public class TaoBaoOAuthLoginForTokenEntity implements Serializable{

	public TaoBaoOAuthLoginForTokenEntity(String requestUrl, String clientId, String redirectUri, String grantType, String code, String clientSecret){
		super();
		this.requestUrl = requestUrl;
		client_id = clientId;
		redirect_uri = redirectUri;
		grant_type = grantType;
		this.code = code;
		client_secret = clientSecret;
	}

	public TaoBaoOAuthLoginForTokenEntity(){
		super();
	}

	private static final long	serialVersionUID	= 4087772680781105962L;

	/**
	 * 访问请求, 请求url,https://oauth.taobao.com/token
	 */
	private String				requestUrl;

	/**
	 * Y 即创建应用时的Appkey
	 */
	private String				client_id;

	/**
	 * Y 登录后的回调地址，（注意：此地址必须要与注册应用时的回调地址相匹配，匹配规则是：注域名完全匹配
	 */
	private String				redirect_uri;

	/**
	 * Y 授权类型 authorization_code 或者 refresh_token
	 */
	private String				grant_type;

	/**
	 * Y 授权请求中的授权码,即第一步获取到的code
	 */
	private String				code;

	/**
	 * Y 客户密钥，即appsecret
	 */
	private String				client_secret;

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

	/**
	 * Y 授权类型 authorization_code 或者 refresh_token
	 * 
	 * @return the grant_type
	 */
	public String getGrant_type(){
		return grant_type;
	}

	/**
	 * Y 授权类型 authorization_code 或者 refresh_token
	 * 
	 * @param grantType
	 *            the grant_type to set
	 */
	public void setGrant_type(String grantType){
		grant_type = grantType;
	}

	/**
	 * Y 授权请求中的授权码,即第一步获取到的code
	 * 
	 * @return the code
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Y 授权请求中的授权码,即第一步获取到的code
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * Y 客户密钥，即appsecret
	 * 
	 * @return the client_secret
	 */
	public String getClient_secret(){
		return client_secret;
	}

	/**
	 * Y 客户密钥，即appsecret
	 * 
	 * @param clientSecret
	 *            the client_secret to set
	 */
	public void setClient_secret(String clientSecret){
		client_secret = clientSecret;
	}
}
