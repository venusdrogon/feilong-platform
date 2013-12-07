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
package com.feilong.tools.mail.entity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 1:05:45 AM
 */
public abstract class BaseMailEntity{

	/**
	 * 发送邮件的服务器的IP <br>
	 * example:smtp.126.com
	 */
	private String		mailServerHost;

	/**
	 * 邮件服务的端口 默认25
	 */
	private String		mailServerPort	= "25";

	/**
	 * 邮件发送者的地址<br>
	 * example:huanyuansp@126.com
	 */
	private String		fromAddress;

	/**
	 * 登陆邮件发送服务器的用户名<br>
	 * example:huanyuansp@126.com
	 */
	private String		userName;

	/**
	 * 登陆邮件发送服务器的密码<br>
	 * example:******
	 */
	private String		password;

	/**
	 * 邮件接收者的地址
	 */
	private String		toAddress;

	/**
	 * 邮件多人接收地址
	 */
	private String[]	toAddresss;

	/**
	 * 是否需要身份验证,默认 true
	 */
	private boolean		validate		= true;

	/**
	 * 邮件主题
	 */
	private String		subject;

	/**
	 * 邮件的文本内容
	 */
	private String		content;

	/**
	 * 个人名义
	 */
	private String		personal		= "";

	/**
	 * 邮件多人接收地址
	 * 
	 * @return
	 */
	public String[] getToAddresss(){
		return toAddresss;
	}

	/**
	 * 邮件多人接收地址
	 * 
	 * @param toAddresss
	 */
	public void setToAddresss(String[] toAddresss){
		this.toAddresss = toAddresss;
	}

	/**
	 * 发送邮件的服务器的IP <br>
	 * example:smtp.126.com
	 * 
	 * @return
	 */
	public String getMailServerHost(){
		return mailServerHost;
	}

	/**
	 * 发送邮件的服务器的IP <br>
	 * example:smtp.126.com
	 * 
	 * @param mailServerHost
	 */
	public void setMailServerHost(String mailServerHost){
		this.mailServerHost = mailServerHost;
	}

	/**
	 * 邮件服务的端口 默认25
	 * 
	 * @return
	 */
	public String getMailServerPort(){
		return mailServerPort;
	}

	/**
	 * 邮件服务的端口 默认25
	 * 
	 * @param mailServerPort
	 */
	public void setMailServerPort(String mailServerPort){
		this.mailServerPort = mailServerPort;
	}

	/**
	 * 是否需要身份验证,默认 true
	 * 
	 * @return
	 */
	public boolean isValidate(){
		return validate;
	}

	/**
	 * 是否需要身份验证,默认 true
	 * 
	 * @param validate
	 */
	public void setValidate(boolean validate){
		this.validate = validate;
	}

	/**
	 * 邮件发送者的地址 example:huanyuansp@126.com
	 * 
	 * @return
	 */
	public String getFromAddress(){
		return fromAddress;
	}

	/**
	 * 邮件发送者的地址 example:huanyuansp@126.com
	 * 
	 * @param fromAddress
	 */
	public void setFromAddress(String fromAddress){
		this.fromAddress = fromAddress;
	}

	/**
	 * 登陆邮件发送服务器的密码
	 * 
	 * @return
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * 登陆邮件发送服务器的密码
	 * 
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * 邮件接收者的地址
	 * 
	 * @return
	 */
	public String getToAddress(){
		return toAddress;
	}

	/**
	 * 邮件接收者的地址
	 * 
	 * @param toAddress
	 */
	public void setToAddress(String toAddress){
		this.toAddress = toAddress;
	}

	/**
	 * 登陆邮件发送服务器的用户名 example:huanyuansp@126.com
	 */
	public String getUserName(){
		return userName;
	}

	/**
	 * 登陆邮件发送服务器的用户名 example:huanyuansp@126.com
	 * 
	 * @param userName
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * 邮件主题
	 * 
	 * @return
	 */
	public String getSubject(){
		return subject;
	}

	/**
	 * 邮件主题
	 * 
	 * @param subject
	 */
	public void setSubject(String subject){
		this.subject = subject;
	}

	/**
	 * 邮件的文本内容
	 * 
	 * @return
	 */
	public String getContent(){
		return content;
	}

	/**
	 * 邮件的文本内容
	 * 
	 * @param textContent
	 */
	public void setContent(String textContent){
		this.content = textContent;
	}

	/**
	 * 个人名义
	 * 
	 * @return the personal
	 */
	public String getPersonal(){
		return personal;
	}

	/**
	 * 个人名义
	 * 
	 * @param personal
	 *            the personal to set
	 */
	public void setPersonal(String personal){
		this.personal = personal;
	}
}
