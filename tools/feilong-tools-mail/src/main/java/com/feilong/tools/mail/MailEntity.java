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
package com.feilong.tools.mail;

import java.util.List;

/**
 * The Class BaseMailEntity.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 1:05:45 AM
 */
public class MailEntity{

	/** 是否debug 输出. */
	private boolean			isDebug				= false;

	/** 是否需要回执, 默认不需要. */
	private boolean			isNeedReturnReceipt	= false;

	// *******************************************************************
	/**
	 * 发送邮件的服务器的IP <br>
	 * example:smtp.126.com
	 */
	private String			mailServerHost;

	/** 邮件服务的端口 默认25. */
	private String			mailServerPort		= "25";

	// *******************************************************************
	/**
	 * 邮件发送者的地址<br>
	 * example:huanyuansp@126.com
	 */
	private String			fromAddress;

	// *******************************************************************

	/**
	 * 登陆邮件发送服务器的用户名<br>
	 * example: venusdrogon@163.com
	 */
	private String			userName;

	/**
	 * 登陆邮件发送服务器的密码<br>
	 * example:******.
	 */
	private String			password;

	/** 个人名义. */
	private String			personal			= "";

	/** 是否需要身份验证,默认 true. */
	private boolean			validate			= true;

	// ***************************************************************

	/** 邮件多人接收地址. */
	private String[]		tos;

	/** 邮件多人接收地址(抄送). */
	private String[]		ccs;

	/** 邮件多人接收地址. */
	private String[]		bccs;

	// ***************************************************************
	/** 优先级. */
	private Priority		priority;

	// *******************************************************************

	/** 邮件主题. */
	private String			subject;

	/** 邮件的文本内容. */
	private String			content;

	// ****************附件***************************************************

	/** 邮件附件的文件名. */
	private String[]		attachFileNames;

	/** 获取图片的二进制. */
	private List<byte[]>	attachList;

	// *******************************************************************

	/**
	 * 邮件附件的文件名
	 * 
	 * @return attachFileNames
	 */
	public String[] getAttachFileNames(){
		return attachFileNames;
	}

	/**
	 * 邮件附件的文件名
	 * 
	 * @param fileNames
	 *            the new 邮件附件的文件名
	 */
	public void setAttachFileNames(String[] fileNames){
		this.attachFileNames = fileNames;
	}

	/**
	 * 发送邮件的服务器的IP <br>
	 * example:smtp.126.com
	 * 
	 * @return the 发送邮件的服务器的IP <br>
	 *         example:smtp
	 */
	public String getMailServerHost(){
		return mailServerHost;
	}

	/**
	 * 发送邮件的服务器的IP <br>
	 * example:smtp.126.com
	 * 
	 * @param mailServerHost
	 *            the new 发送邮件的服务器的IP <br>
	 *            example:smtp
	 */
	public void setMailServerHost(String mailServerHost){
		this.mailServerHost = mailServerHost;
	}

	/**
	 * 邮件服务的端口 默认25
	 * 
	 * @return the 邮件服务的端口 默认25
	 */
	public String getMailServerPort(){
		return mailServerPort;
	}

	/**
	 * 邮件服务的端口 默认25
	 * 
	 * @param mailServerPort
	 *            the new 邮件服务的端口 默认25
	 */
	public void setMailServerPort(String mailServerPort){
		this.mailServerPort = mailServerPort;
	}

	/**
	 * 是否需要身份验证,默认 true
	 * 
	 * @return the 是否需要身份验证,默认 true
	 */
	public boolean isValidate(){
		return validate;
	}

	/**
	 * 是否需要身份验证,默认 true
	 * 
	 * @param validate
	 *            the new 是否需要身份验证,默认 true
	 */
	public void setValidate(boolean validate){
		this.validate = validate;
	}

	/**
	 * 邮件发送者的地址 example:huanyuansp@126.com
	 * 
	 * @return the 邮件发送者的地址<br>
	 *         example:huanyuansp@126
	 */
	public String getFromAddress(){
		return fromAddress;
	}

	/**
	 * 邮件发送者的地址 example:huanyuansp@126.com
	 * 
	 * @param fromAddress
	 *            the new 邮件发送者的地址<br>
	 *            example:huanyuansp@126
	 */
	public void setFromAddress(String fromAddress){
		this.fromAddress = fromAddress;
	}

	/**
	 * 登陆邮件发送服务器的密码
	 * 
	 * @return the 登陆邮件发送服务器的密码<br>
	 *         example:******
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * 登陆邮件发送服务器的密码
	 * 
	 * @param password
	 *            the new 登陆邮件发送服务器的密码<br>
	 *            example:******
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * 登陆邮件发送服务器的用户名 example:huanyuansp@126.com
	 * 
	 * @return the 登陆邮件发送服务器的用户名<br>
	 *         example:huanyuansp@126
	 */
	public String getUserName(){
		return userName;
	}

	/**
	 * 登陆邮件发送服务器的用户名 example:huanyuansp@126.com
	 * 
	 * @param userName
	 *            the new 登陆邮件发送服务器的用户名<br>
	 *            example:huanyuansp@126
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * 邮件主题
	 * 
	 * @return the 邮件主题
	 */
	public String getSubject(){
		return subject;
	}

	/**
	 * 邮件主题
	 * 
	 * @param subject
	 *            the new 邮件主题
	 */
	public void setSubject(String subject){
		this.subject = subject;
	}

	/**
	 * 邮件的文本内容
	 * 
	 * @return the 邮件的文本内容
	 */
	public String getContent(){
		return content;
	}

	/**
	 * 邮件的文本内容
	 * 
	 * @param textContent
	 *            the new 邮件的文本内容
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

	/**
	 * Gets the 优先级.
	 * 
	 * @return the priority
	 */
	public Priority getPriority(){
		return priority;
	}

	/**
	 * Sets the 优先级.
	 * 
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priority priority){
		this.priority = priority;
	}

	/**
	 * Gets the 邮件多人接收地址.
	 * 
	 * @return the tos
	 */
	public String[] getTos(){
		return tos;
	}

	/**
	 * Sets the 邮件多人接收地址.
	 * 
	 * @param tos
	 *            the tos to set
	 */
	public void setTos(String[] tos){
		this.tos = tos;
	}

	/**
	 * Gets the 邮件多人接收地址(抄送).
	 * 
	 * @return the ccs
	 */
	public String[] getCcs(){
		return ccs;
	}

	/**
	 * Sets the 邮件多人接收地址(抄送).
	 * 
	 * @param ccs
	 *            the ccs to set
	 */
	public void setCcs(String[] ccs){
		this.ccs = ccs;
	}

	/**
	 * Gets the 邮件多人接收地址.
	 * 
	 * @return the bccs
	 */
	public String[] getBccs(){
		return bccs;
	}

	/**
	 * Sets the 邮件多人接收地址.
	 * 
	 * @param bccs
	 *            the bccs to set
	 */
	public void setBccs(String[] bccs){
		this.bccs = bccs;
	}

	/**
	 * Gets the 是否debug 输出.
	 * 
	 * @return the isDebug
	 */
	public boolean getIsDebug(){
		return isDebug;
	}

	/**
	 * Sets the 是否debug 输出.
	 * 
	 * @param isDebug
	 *            the isDebug to set
	 */
	public void setIsDebug(boolean isDebug){
		this.isDebug = isDebug;
	}

	/**
	 * Gets the 是否需要回执, 默认不需要.
	 * 
	 * @return the isNeedReturnReceipt
	 */
	public boolean getIsNeedReturnReceipt(){
		return isNeedReturnReceipt;
	}

	/**
	 * Sets the 是否需要回执, 默认不需要.
	 * 
	 * @param isNeedReturnReceipt
	 *            the isNeedReturnReceipt to set
	 */
	public void setIsNeedReturnReceipt(boolean isNeedReturnReceipt){
		this.isNeedReturnReceipt = isNeedReturnReceipt;
	}

	/**
	 * @return the attachList
	 */
	public List<byte[]> getAttachList(){
		return attachList;
	}

	/**
	 * @param attachList
	 *            the attachList to set
	 */
	public void setAttachList(List<byte[]> attachList){
		this.attachList = attachList;
	}

}
