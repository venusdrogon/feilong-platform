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
package com.feilong.taglib.common.middleware;

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.reference.ip.IpUtil;

/**
 * ip地址自动转换成地区
 * 
 * @author 金鑫 2009-9-4下午01:16:19
 */
public class IpTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * ip地址
	 */
	private String				ip;

	/**
	 * 显示内容 region 地区 business:运营商 all 地区+运营商 默认地区
	 */
	private String				showContent			= "region";

	@Override
	public String writeContent(){
		return IpUtil.ipToAddress(ip, showContent);
	}

	/*******************************************************************************/
	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip){
		this.ip = ip;
	}

	/**
	 * @param showContent
	 *            the showContent to set
	 */
	public void setShowContent(String showContent){
		this.showContent = showContent;
	}
}
