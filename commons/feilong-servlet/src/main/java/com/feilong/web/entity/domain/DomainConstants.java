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

import java.util.ResourceBundle;

import com.feilong.commons.core.configure.ResourceBundleUtil;

/**
 * 相关二级域名 配置
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-9 下午4:37:46
 */
public interface DomainConstants{

	/** 接口配置. */
	ResourceBundle	domain			= ResourceBundle.getBundle("config/domain");

	/** 当前项目配置的 css的域名. */
	String			DOMAIN_CSS		= ResourceBundleUtil.getValue(domain, "domain.css");

	/** 当前项目配置的 js的域名. */
	String			DOMAIN_JS		= ResourceBundleUtil.getValue(domain, "domain.js");

	/** 当前项目配置的 image 的域名. */
	String			DOMAIN_IMAGE	= ResourceBundleUtil.getValue(domain, "domain.image");

	/** 当前项目配置的 image 的域名(PDP). */
	String			DOMAIN_RESOURCE	= ResourceBundleUtil.getValue(domain, "domain.resource");

	/** 商城的网址，一般用于不同环境 第三方数据传递 比如微博等. */
	String			DOMAIN_STORE	= ResourceBundleUtil.getValue(domain, "domain.store");

}
