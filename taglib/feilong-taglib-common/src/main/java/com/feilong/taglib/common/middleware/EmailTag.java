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

/**
 * 邮箱显示其名称及登录路径
 * 
 * @author 金鑫 2010-3-25 下午02:42:49
 */
@Deprecated
public class EmailTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 邮箱
	 */
	private String				email;

	/**
	 * 显示内容 仅支持
	 * 
	 * <pre>
	 * 
	 * chineseName :中文名
	 * loginHref :登录路径 
	 * all	:所有
	 * </pre>
	 * 
	 * 默认all
	 */
	private String				showContent			= "all";

	@Override
	public String writeContent(){
		// if ("all".equals(showContent)){// 所有
		// return EmailUtil.getEmailLoginHrefByEmail(email);
		// }else if ("chineseName".equals(showContent)){// 中文名
		// return EmailUtil.getEmailChineseName(email);
		// }else if ("loginHref".equals(showContent)){// 登录路径
		// return EmailUtil.getEmailLoginHrefByEmail(email);
		// }
		return "";
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * @param showContent
	 *            the showContent to set
	 */
	public void setShowContent(String showContent){
		this.showContent = showContent;
	}
}
