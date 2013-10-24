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
package com.feilong.tools.jsoup.entity;

import java.io.Serializable;

/**
 * 企业信息
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-12 上午01:17:46
 */
public class Enterprise implements Serializable{

	private static final long	serialVersionUID	= 3862419791800963668L;

	/**
	 * 企业名称
	 */
	private String				name;

	/**
	 * 电话
	 */
	private String				telephone;

	/**
	 * email
	 */
	private String				email;

	/**
	 * 联系人
	 */
	private String				linkMan;

	/**
	 * 地址(招聘网站)
	 */
	private String				pageUrl;

	//******************************************************************
	/**
	 * 企业名称
	 * 
	 * @return
	 */
	public String getName(){
		return name;
	}

	/**
	 * 企业名称
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 电话
	 * 
	 * @return
	 */
	public String getTelephone(){
		return telephone;
	}

	/**
	 * 电话
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	/**
	 * email
	 * 
	 * @return
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * email
	 * 
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * 联系人
	 * 
	 * @return
	 */
	public String getLinkMan(){
		return linkMan;
	}

	/**
	 * 联系人
	 * 
	 * @param linkMan
	 */
	public void setLinkMan(String linkMan){
		this.linkMan = linkMan;
	}

	/**
	 * 地址(招聘网站)
	 * 
	 * @return
	 */
	public String getPageUrl(){
		return pageUrl;
	}

	/**
	 * 地址(招聘网站)
	 * 
	 * @param pageUrl
	 */
	public void setPageUrl(String pageUrl){
		this.pageUrl = pageUrl;
	}
}
