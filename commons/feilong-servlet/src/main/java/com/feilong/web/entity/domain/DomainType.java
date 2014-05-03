/**
 * Copyright (c) 2008-2012 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.web.entity.domain;

/**
 * 二级域名 类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-13 下午5:49:47
 */
public enum DomainType{

	/** 样式表. */
	CSS(DomainConstants.DOMAIN_CSS),

	/** js. */
	JS(DomainConstants.DOMAIN_JS),

	/** 图片. */
	IMAGE(DomainConstants.DOMAIN_IMAGE),

	/** 资源图片,如PDP商品图片,在测试环境 可能 商品图片使用外部 而Image图片使用内部. */
	RESOURCE(DomainConstants.DOMAIN_RESOURCE),

	/** 商城的网址，一般用于不同环境 第三方数据传递 比如微博等. */
	STORE(DomainConstants.DOMAIN_STORE);

	private String	path;

	/**
	 * @param path
	 */
	private DomainType(String path){
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public String getPath(){
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path){
		this.path = path;
	}

}
